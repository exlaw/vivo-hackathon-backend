package vivo.chainpaper.exception;

import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;
import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends Exception {
    private WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("已存在重复数据"), HttpStatus.CONFLICT);

    public WrongResponseEntity getResponse() {
        return response;
    }
}
