package vivo.chainpaper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "star")
public class Star {
    @Id
    @Column(name="id")
    String id;

    @Column(name="userId")
    String userId;

    @Column(name="paperId")
    String paperId;

    @Column(name="star")
    int star=-1;//0 不star , 1 star

    @Column(name="score")
    int score=0;//评分 1-10

    public Star(String userId, String paperId) {
        this.userId = userId;
        this.paperId = paperId;
        this.id=userId+paperId;
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

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
