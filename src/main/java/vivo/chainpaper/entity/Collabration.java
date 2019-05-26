package vivo.chainpaper.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "collabration")
public class Collabration {
    @Id
    private  String collabrationID;

    @Column(name="from_username")
    private  String fromUsername;

    @Column(name="to_username")
    private  String toUsername;

    @Column(name="time")
    private  String time;

    @Column(name="paper_id")
    private  String paperId;

    @Column(name="type")
    private  String type;

    @Column(name="state")
    private  boolean state;

    public Collabration() {
    }

    public Collabration(String collabrationID, String fromUsername, String toUsername, String time, String paperId, String type, Boolean state) {
        this.collabrationID = collabrationID;
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
        this.time = time;
        this.paperId = paperId;
        this.type = type;
        this.state = state;
    }

    public String getCollabrationID() {
        return collabrationID;
    }

    public void setCollabrationID(String collabrationID) {
        this.collabrationID = collabrationID;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
