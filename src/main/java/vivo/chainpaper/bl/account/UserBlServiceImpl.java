package vivo.chainpaper.bl.account;

import vivo.chainpaper.blservice.account.UserBlService;
import vivo.chainpaper.dao.account.UserDao;
import vivo.chainpaper.entity.Role;
import vivo.chainpaper.entity.account.User;
import vivo.chainpaper.exception.*;
import vivo.chainpaper.parameters.user.EmailValidationParams;
import vivo.chainpaper.parameters.user.RegisterParams;
import vivo.chainpaper.parameters.user.UserInfoParams;
import vivo.chainpaper.response.SuccessResponse;
import vivo.chainpaper.response.user.EmailValidationRequestResponse;
import vivo.chainpaper.response.user.LoginResponse;
import vivo.chainpaper.response.user.RegisterResponse;
import vivo.chainpaper.response.user.UserInfoResponse;
import vivo.chainpaper.security.jwt.JwtService;
import vivo.chainpaper.security.jwt.JwtUser;
import vivo.chainpaper.security.jwt.JwtUserDetailsService;
import vivo.chainpaper.util.RandomUtil;
import vivo.chainpaper.util.RoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBlServiceImpl implements UserBlService {

    private final static long EXPIRATION = 604800;
    @Value("${email.sender}")
    private String senderEmail;
    @Value("${email.subject}")
    private String subject;
    @Value("${email.content1}")
    private String content1;
    @Value("${email.content2}")
    private String content2;

    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtService jwtService;
    private final UserDao userDao;
    private final JavaMailSender mailSender;

    @Autowired
    public UserBlServiceImpl(JwtUserDetailsService jwtUserDetailsService, JwtService jwtService, UserDao userDao, JavaMailSender mailSender) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtService = jwtService;
        this.userDao = userDao;
        this.mailSender = mailSender;
    }


    /**
     * login
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the login info to response
     * @throws WrongUsernameOrPasswordException the username or password is error
     */
    @Override
    public LoginResponse login(String username, String password) throws WrongUsernameOrPasswordException, UsernameDoesNotFoundException {
        User user = userDao.findUserByUsernameAndIsActive(username, true);
        if (user != null) {
            if (!user.getPassword().equals(password) || !user.isValidated()) {
                throw new WrongUsernameOrPasswordException();
            } else {
                JwtUser jwtUser = (JwtUser) jwtUserDetailsService.loadUserByUsername(username);
                String token = jwtService.generateToken(jwtUser, EXPIRATION);
                return new LoginResponse(token, user.getRole(), user.getAvatarUrl(), username, user.getRealName(), user.isValidated());
            }
        } else {
            throw new UsernameDoesNotFoundException();
        }
    }

    /**
     * @param params
     * @return
     */
    @Override
    public RegisterResponse register(RegisterParams params) throws UserAlreadyExistsException {
        if (userDao.findUserByUsernameAndIsActive(params.getUsername(), true) != null) {
            throw new UserAlreadyExistsException();
        } else {
            userDao.save(new User(String.format("http://identicon.relucks.org/%s?size=96", RandomUtil.generateCode(6)), params.getUsername(), params.getEmail(), "", "", params.getEmail().split("@")[0], params.getPassword(), RoleUtil.ROLE_TABLE.get(params.getRole()), RandomUtil.generateUUID(), RandomUtil.generateCode(6), false, true, System.currentTimeMillis()));
            JwtUser jwtUser = (JwtUser) jwtUserDetailsService.loadUserByUsername(params.getUsername());
            String token = jwtService.generateToken(jwtUser, EXPIRATION);
            return new RegisterResponse(token);
        }
    }

    /**
     * logoff
     *
     * @param username
     */
    @Override
    public void logoff(String username) throws UsernameDoesNotFoundException {
        User user = userDao.findUserByUsernameAndIsActive(username, true);
        if (user != null) {
            user.setActive(false);
            userDao.save(user);
        } else {
            throw new UsernameDoesNotFoundException();
        }
    }

    @Override
    public EmailValidationRequestResponse requestEmailValidation(String username) throws UsernameDoesNotFoundException, InvalidEmailAddressesException {
        User user = userDao.findUserByUsernameAndIsActive(username, true);
        if (user != null) {
            sendEmail(user.getValidationCode(), user.getEmail());
            return new EmailValidationRequestResponse(user.getValidationToken());
        } else {
            throw new UsernameDoesNotFoundException();
        }
    }

    @Override
    public void validateEmail(String username, EmailValidationParams params) throws UsernameDoesNotFoundException, InvalidCodeException {
        User user = userDao.findUserByUsernameAndIsActive(username, true);
        if (user != null) {
            if (!(user.getValidationToken().equals(params.getValidationToken()) && user.getValidationCode().equals(params.getValidationCode()))) {
                throw new InvalidCodeException();
            } else {
                user.setValidated(true);
                userDao.save(user);
            }
        } else {
            throw new UsernameDoesNotFoundException();
        }
    }

    @Override
    public UserInfoResponse getUserInfo(String username) throws UsernameDoesNotFoundException {
        User user = userDao.findUserByUsernameAndIsActive(username, true);
        if (user != null) {
            return new UserInfoResponse(user.getAvatarUrl(), user.getRealName(), user.getNumber(), user.getPhone(), user.getEmail());
        } else {
            throw new UsernameDoesNotFoundException();
        }
    }

    @Override
    public SuccessResponse postUserInfo(UserInfoParams userInfoParam, String username) {
        User user = userDao.findUserByUsername(username);
        if (user != null) {
            user.setRealName(userInfoParam.getRealName());
            user.setNumber(userInfoParam.getStudentNumber());
            user.setPhone(userInfoParam.getPhone());
            userDao.save(user);
        }
        return new SuccessResponse();
    }

    /**
     * send email to an user
     *
     * @param code  the validation code
     * @param email the email address
     */
    public void sendEmail(String code, String email) throws InvalidEmailAddressesException {
        SimpleMailMessage message = new SimpleMailMessage();
        String content = content1 + code + content2;
        message.setFrom(senderEmail);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidEmailAddressesException();
        }
    }

    public void sendEmailWithContent(String content, String email) throws InvalidEmailAddressesException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(email);
        message.setSubject("MyCourses邮件群发");
        message.setText(content);

        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidEmailAddressesException();
        }
    }

}
