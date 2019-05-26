package vivo.chainpaper.entity;

import vivo.chainpaper.util.TimeUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

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
    String time_stamp;

    @Column(name ="comment")
    String comment;

    public Comment( String userId, String paperId, String comment) {
        this.time_stamp= TimeUtil.getTimeStamp();
        this.userId = userId;
        this.paperId = paperId;
        this.id = time_stamp +userId+paperId;
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

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
