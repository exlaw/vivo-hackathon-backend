package vivo.chainpaper.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrongResponseEntity extends ResponseEntity<Response> {

    public WrongResponseEntity(Response response, HttpStatus httpStatus) {
        super(response, httpStatus);
    }

}
