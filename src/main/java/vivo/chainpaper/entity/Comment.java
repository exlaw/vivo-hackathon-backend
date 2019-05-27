package vivo.chainpaper.entity;

import vivo.chainpaper.util.TimeUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name="id")
    String id;

    @Column(name="userId")
    String userId;

    @Column(name="paperId")
    String paperId;

    @Column(name="time_stamp")
    String timeStamp;

    @Column(name ="comment")
    String comment;

    public Comment() {
    }

    public Comment(String userId, String paperId, String comment) {
        this.timeStamp= TimeUtil.getTimeStamp();
        this.userId = userId;
        this.paperId = paperId;
        this.id = timeStamp +userId+paperId;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
