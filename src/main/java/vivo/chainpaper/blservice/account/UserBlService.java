package vivo.chainpaper.blservice.account;

import vivo.chainpaper.parameters.user.EmailValidationParams;
import vivo.chainpaper.parameters.user.RegisterParams;
import vivo.chainpaper.parameters.user.UserInfoParams;
import vivo.chainpaper.response.SuccessResponse;
import org.springframework.stereotype.Service;
import vivo.chainpaper.exception.*;
import vivo.chainpaper.response.user.LoginResponse;
import vivo.chainpaper.response.user.RegisterResponse;
import vivo.chainpaper.response.user.UserInfoResponse;

@Service
public interface UserBlService {
    /**
     * login
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the login info to  response
     * @throws WrongUsernameOrPasswordException the username or password is error
     */
    LoginResponse login(String username, String password) throws WrongUsernameOrPasswordException, CannotRegisterException, UsernameDoesNotFoundException;

    /**
     * @param params
     * @return
     */
    RegisterResponse register(RegisterParams params) throws UserAlreadyExistsException;

    /**
     * logoff
     *
     * @param username
     */
    void logoff(String username) throws UsernameDoesNotFoundException;

    /**
     * validate email
     *
     * @param username
     * @param params
     * @throws UsernameDoesNotFoundException
     * @throws InvalidCodeException
     */
    void validateEmail(String username, EmailValidationParams params) throws UsernameDoesNotFoundException, InvalidCodeException;

    /**
     * get info of user
     *
     * @param username
     * @return
     * @throws UsernameDoesNotFoundException
     */
    UserInfoResponse getUserInfo(String username) throws UsernameDoesNotFoundException;


    SuccessResponse postUserInfo(UserInfoParams userInfoParam, String username);

    void sendEmail(String code, String email) throws InvalidEmailAddressesException;

    void sendEmailWithContent(String content, String email) throws InvalidEmailAddressesException;
}
