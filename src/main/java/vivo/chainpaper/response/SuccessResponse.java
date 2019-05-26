package vivo.chainpaper.response;

public class SuccessResponse extends Response {
    private String description;

    public SuccessResponse() {
        this.description = "操作成功";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
