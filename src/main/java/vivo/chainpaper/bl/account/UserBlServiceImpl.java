package vivo.chainpaper.bl.account;

import vivo.chainpaper.dao.account.UserDao;
import vivo.chainpaper.entity.account.User;
import vivo.chainpaper.exception.*;
import vivo.chainpaper.parameters.user.RegisterParams;
import vivo.chainpaper.response.user.LoginResponse;
import vivo.chainpaper.response.user.RegisterResponse;
import vivo.chainpaper.security.jwt.JwtService;
import vivo.chainpaper.security.jwt.JwtUser;
import vivo.chainpaper.security.jwt.JwtUserDetailsService;
import vivo.chainpaper.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserBlServiceImpl {

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
    public LoginResponse login(String username, String password) throws WrongUsernameOrPasswordException, UsernameDoesNotFoundException {
        User user = userDao.findUserByUsername(username);
        if (user != null) {
            if (!user.getPassword().equals(password)) {
                throw new WrongUsernameOrPasswordException();
            } else {
                JwtUser jwtUser = (JwtUser) jwtUserDetailsService.loadUserByUsername(username);
                String token = jwtService.generateToken(jwtUser, EXPIRATION);
                return new LoginResponse(token, user.getUsername());
            }
        } else {
            throw new UsernameDoesNotFoundException();
        }
    }

    /**
     * @param params
     * @return
     */
    public RegisterResponse register(RegisterParams params) throws UserAlreadyExistsException {
        if (userDao.findUserByUsername(params.getUsername()) != null) {
            throw new UserAlreadyExistsException();
        } else {
            User new_user  = new User(String.format("http://identicon.relucks.org/%s?size=96", RandomUtil.generateCode(6)), params.getUsername(), params.getPassword(), params.getRole(), RandomUtil.generateUUID());
            userDao.save(new_user);
            System.out.println(params.getUsername());
            JwtUser jwtUser = (JwtUser) jwtUserDetailsService.loadUserByUsername(params.getUsername());
            String token = jwtService.generateToken(jwtUser, EXPIRATION);
            return new RegisterResponse(token, new_user.getUsername());
        }
    }

}
