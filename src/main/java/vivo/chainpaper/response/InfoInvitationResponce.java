package vivo.chainpaper.response;

public class InfoInvitationResponce extends Response {
    /**
     * time: string;
     *      * inviteeId: string; // 被接受的用户ID
     *      * inviterId: string; // 发出邀请的ID
     *      * paperId: string; // 要一起合作的文章ID
     */

    private  String time;
    private String inviteeId;
    private String inviterId;
    private String paperId;

    public InfoInvitationResponce(String time, String inviteeId, String inviterId, String paperId) {
        this.time = time;
        this.inviteeId = inviteeId;
        this.inviterId = inviterId;
        this.paperId = paperId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInviteeId() {
        return inviteeId;
    }

    public void setInviteeId(String inviteeId) {
        this.inviteeId = inviteeId;
    }

    public String getInviterId() {
        return inviterId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
}
