package vivo.chainpaper.response;

public class InvitationResponse extends Response {

    private String collabrationInvitationId;

    public InvitationResponse(String collabrationInvitationId) {
        this.collabrationInvitationId = collabrationInvitationId;
    }

    public String getCollabrationInvitationId() {
        return collabrationInvitationId;
    }

    public void setCollabrationInvitationId(String collabrationInvitationId) {
        this.collabrationInvitationId = collabrationInvitationId;
    }

}
