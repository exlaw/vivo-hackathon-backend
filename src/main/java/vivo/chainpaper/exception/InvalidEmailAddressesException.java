package vivo.chainpaper.exception;

import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;
import org.springframework.http.HttpStatus;

public class InvalidEmailAddressesException extends Exception {
    private WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("无效的邮箱地址"), HttpStatus.BAD_REQUEST);

    public WrongResponseEntity getResponse() {
        return response;
    }
}
