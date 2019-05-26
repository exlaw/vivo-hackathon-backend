package vivo.chainpaper.exception;

import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;
import org.springframework.http.HttpStatus;

public class WrongUsernameOrPasswordException extends Exception {
    private WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("错误的用户名或密码"), HttpStatus.UNAUTHORIZED);

    public WrongResponseEntity getResponse() {
        return response;
    }
}
