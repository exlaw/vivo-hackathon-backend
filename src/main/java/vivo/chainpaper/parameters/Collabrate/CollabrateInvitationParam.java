package vivo.chainpaper.parameters.Collabrate;


public class CollabrateInvitationParam {

    private String paperId;

    private String inviteeId;

    public CollabrateInvitationParam(String paperId, String inviteeId) {
        this.paperId = paperId;
        this.inviteeId = inviteeId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getInviteeId() {
        return inviteeId;
    }

    public void setInviteeId(String inviteeId) {
        this.inviteeId = inviteeId;
    }
}
