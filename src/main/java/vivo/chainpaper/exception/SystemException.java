package vivo.chainpaper.exception;


import org.springframework.http.HttpStatus;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;

import javax.transaction.Transactional;

public class SystemException extends Exception {
    private final WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("系统错误"), HttpStatus.SERVICE_UNAVAILABLE);

    public WrongResponseEntity getResponse() {
        return response;
    }
}
