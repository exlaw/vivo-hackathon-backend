package vivo.chainpaper.exception;

import org.springframework.http.HttpStatus;
import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;

public class InvalidCodeException extends Exception {
    private final WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("错误的验证码"), HttpStatus.FORBIDDEN);

    public WrongResponseEntity getResponse() {
        return response;
    }
}