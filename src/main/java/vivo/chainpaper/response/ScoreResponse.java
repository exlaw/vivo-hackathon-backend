package vivo.chainpaper.response;

import io.swagger.models.auth.In;

public class ScoreResponse extends Response {
    private Integer score;
    private Integer myScore;

    public ScoreResponse(Integer score, Integer myScore) {
        this.score = score;

        this.myScore = myScore;
        if (myScore == 0) {
            this.myScore = -1;
        }
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMyScore() {
        return myScore;
    }

    public void setMyScore(Integer myScore) {
        this.myScore = myScore;
    }
}
