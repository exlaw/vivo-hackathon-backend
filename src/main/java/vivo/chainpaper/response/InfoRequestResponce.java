package vivo.chainpaper.response;

public class InfoRequestResponce extends Response{
     /**
    *  time: string;
      * requesteeId: string; // 被请求用户的ID
      * requesterId: string; // 请求发起的用户ID
      * paperId: string; // 要一起合作的文章ID
     */

    private  String time;
    private String requesteeId;
    private String requesterId;
    private String paperId;
    private String collabrationRequestId;

    public InfoRequestResponce(String time, String requesteeId, String requesterId, String paperId, String collabrationInvitationId) {
        this.time = time;
        this.requesteeId = requesteeId;
        this.requesterId = requesterId;
        this.paperId = paperId;
        this.collabrationRequestId = collabrationInvitationId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRequesteeId() {
        return requesteeId;
    }

    public void setRequesteeId(String requesteeId) {
        this.requesteeId = requesteeId;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getCollabrationInvitationId() {
        return collabrationRequestId;
    }

    public void setCollabrationInvitationId(String collabrationInvitationId) {
        this.collabrationRequestId = collabrationInvitationId;
    }
}
