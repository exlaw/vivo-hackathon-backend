package vivo.chainpaper.exception;

import org.springframework.http.HttpStatus;
import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;

public class UsernameDoesNotFoundException extends Exception {
    private final WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("找不到用户名"), HttpStatus.NOT_FOUND);

    public WrongResponseEntity getResponse() {
        return response;
    }
}
