package vivo.chainpaper.response.user;

import vivo.chainpaper.response.Response;

public class EmailValidationRequestResponse extends Response {
    private String validationToken;

    public EmailValidationRequestResponse() {
    }

    public EmailValidationRequestResponse(String validationToken) {
        this.validationToken = validationToken;
    }

    public String getValidationToken() {
        return validationToken;
    }

    public void setValidationToken(String validationToken) {
        this.validationToken = validationToken;
    }
}
