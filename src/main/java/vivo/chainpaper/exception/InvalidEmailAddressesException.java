package vivo.chainpaper.exception;

import org.springframework.http.HttpStatus;
import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;

public class InvalidEmailAddressesException extends Exception {
    private final WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("无效的邮箱地址"), HttpStatus.BAD_REQUEST);

    public WrongResponseEntity getResponse() {
        return response;
    }
}
