package vivo.chainpaper.parameters.user;

public class EmailValidationParams {
    private String validationToken;
    private String validationCode;

    public EmailValidationParams() {
    }

    public EmailValidationParams(String validationToken, String validationCode) {
        this.validationToken = validationToken;
        this.validationCode = validationCode;
    }

    public String getValidationToken() {
        return validationToken;
    }

    public void setValidationToken(String validationToken) {
        this.validationToken = validationToken;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }
}
