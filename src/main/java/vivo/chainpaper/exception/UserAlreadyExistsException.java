package vivo.chainpaper.exception;

import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends Exception {
    private final WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("用户已存在"), HttpStatus.CONFLICT);

    public WrongResponseEntity getResponse() {
        return response;
    }
}
