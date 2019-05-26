package vivo.chainpaper.response.upload;

import vivo.chainpaper.response.Response;

public class UploadImageResponse extends Response {
    private String id;
    private String url;

    public UploadImageResponse() {
    }

    public UploadImageResponse(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
