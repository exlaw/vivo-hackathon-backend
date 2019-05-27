package vivo.chainpaper.exception;

import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;
import org.springframework.http.HttpStatus;

public class InvalidCodeException extends Exception {
    private final WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("错误的验证码"), HttpStatus.FORBIDDEN);

    public WrongResponseEntity getResponse() {
        return response;
    }
}