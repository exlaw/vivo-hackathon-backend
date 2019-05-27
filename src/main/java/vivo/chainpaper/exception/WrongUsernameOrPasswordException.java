package vivo.chainpaper.exception;

import org.springframework.http.HttpStatus;
import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;

public class WrongUsernameOrPasswordException extends Exception {
    private  final WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("错误的用户名或密码"), HttpStatus.UNAUTHORIZED);

    public WrongResponseEntity getResponse() {
        return response;
    }
}
