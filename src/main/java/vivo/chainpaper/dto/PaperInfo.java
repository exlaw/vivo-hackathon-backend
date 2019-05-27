package vivo.chainpaper.dto;

import vivo.chainpaper.parameters.paper.PaperDraft;


public class PaperInfo {
    String paperId;
    String[] authors;
    PaperDraft paper;
    String uploadTime;
    int score;
    int stars;
    CommentDto[] comments;
    int state;


    public PaperInfo() {
        state = 0;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public PaperDraft getPaper() {
        return paper;
    }

    public void setPaper(PaperDraft paper) {
        this.paper = paper;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public CommentDto[] getComments() {
        return comments;
    }

    public void setComments(CommentDto[] comments) {
        this.comments = comments;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
