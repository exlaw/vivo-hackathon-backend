package vivo.chainpaper.response;

public class CommentItem {
    private String userId;
    private String  time; // Unix UTC时间戳
    private String  content;

    public CommentItem(String userId, String time, String content) {
        this.userId = userId;
        this.time = time;
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
