package vivo.chainpaper.response;

public class StarResponse extends Response {
    private int stars;
    private boolean stared;

    public StarResponse(int stars, boolean stared) {
        this.stars = stars;
        this.stared = stared;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public boolean isStared() {
        return stared;
    }

    public void setStared(boolean stared) {
        this.stared = stared;
    }
}
