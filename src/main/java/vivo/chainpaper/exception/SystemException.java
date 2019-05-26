package vivo.chainpaper.exception;


import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;
import org.springframework.http.HttpStatus;

public class SystemException extends Exception {
    private WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("系统错误"), HttpStatus.SERVICE_UNAVAILABLE);

    public WrongResponseEntity getResponse() {
        return response;
    }
}
