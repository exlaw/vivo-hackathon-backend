package vivo.chainpaper.exception;

import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;
import org.springframework.http.HttpStatus;

public class CannotRegisterException extends Exception {
    private WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("无法注册"), HttpStatus.SERVICE_UNAVAILABLE);

    public WrongResponseEntity getResponse() {
        return response;
    }
}
