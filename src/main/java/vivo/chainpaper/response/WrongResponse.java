package vivo.chainpaper.response;

public class WrongResponse extends Response {
    private String description;

    public WrongResponse(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
