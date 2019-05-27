package vivo.chainpaper.exception;

import org.springframework.http.HttpStatus;
import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;

public class UserAlreadyExistsException extends Exception {
    private final WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("用户已存在"), HttpStatus.CONFLICT);

    public WrongResponseEntity getResponse() {
        return response;
    }
}
