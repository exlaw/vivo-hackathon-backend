package vivo.chainpaper.response;

public class RequestResponse extends Response{

    private String collabrationRequestId;

    public RequestResponse(String collabrationRequestId) {
        this.collabrationRequestId = collabrationRequestId;
    }

    public String getCollabrationRequestId() {
        return collabrationRequestId;
    }

    public void setCollabrationRequestId(String collabrationRequestId) {
        this.collabrationRequestId = collabrationRequestId;
    }
}
