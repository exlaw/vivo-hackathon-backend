package vivo.chainpaper.response;

import java.util.List;

public class InfoPersonResponse extends Response {
    /**
     *  * 200: {
     *      * userId: string;
     *      * username: string;
     *      * role: "student" | "teacher";
     *      * papers: PaperInfo[]; // 自己上传过的所有论文
     *      * score: number; // 对个人的评分，你们怎么算看你们
     *      * collabrationInvitationIds: string[]; // 自己接受到的合作邀请的ID
     *      * collabrationRequestIds: string[]; // 自己发出的合作请求的ID
     *      * }
     */

    private  String userId;
    private String role;
    private String username;
    private int score;
    private List<String> papers;
    private List<String> collabrationInvitationIds;
    private List<String> collabrationRequestIds;

    public InfoPersonResponse(String userId, String role, String username, int score, List<String> papers, List<String> collabrationInvitationIds, List<String> collabrationRequestIds) {
        this.userId = userId;
        this.role = role;
        this.username = username;
        this.score = score;
        this.papers = papers;
        this.collabrationInvitationIds = collabrationInvitationIds;
        this.collabrationRequestIds = collabrationRequestIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getPapers() {
        return papers;
    }

    public void setPapers(List<String> papers) {
        this.papers = papers;
    }

    public List<String> getCollabrationInvitationIds() {
        return collabrationInvitationIds;
    }

    public void setCollabrationInvitationIds(List<String> collabrationInvitationIds) {
        this.collabrationInvitationIds = collabrationInvitationIds;
    }

    public List<String> getCollabrationRequestIds() {
        return collabrationRequestIds;
    }

    public void setCollabrationRequestIds(List<String> collabrationRequestIds) {
        this.collabrationRequestIds = collabrationRequestIds;
    }
}
