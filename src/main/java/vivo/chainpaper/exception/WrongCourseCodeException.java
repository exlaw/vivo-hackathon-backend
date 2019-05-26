package vivo.chainpaper.exception;

import vivo.chainpaper.response.WrongResponse;
import vivo.chainpaper.response.WrongResponseEntity;
import org.springframework.http.HttpStatus;

public class WrongCourseCodeException extends Exception {
    private WrongResponseEntity response = new WrongResponseEntity(new WrongResponse("错误的选课密码"), HttpStatus.BAD_REQUEST);

    public WrongResponseEntity getResponse() {
        return response;
    }
}
