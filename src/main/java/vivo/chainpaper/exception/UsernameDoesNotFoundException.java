package vivo.chainpaper.exception;

import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;
import org.springframework.http.HttpStatus;

public class UsernameDoesNotFoundException extends Exception {
    private final WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("找不到用户名"), HttpStatus.NOT_FOUND);

    public WrongResponseEntity getResponse() {
        return response;
    }
}
