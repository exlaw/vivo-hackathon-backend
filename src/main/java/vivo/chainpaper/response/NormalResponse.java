package vivo.chainpaper.response;

public class NormalResponse extends Response {
    private String first;

    private String second;

    private String third;

    private String forth;


    public NormalResponse(String first, String second, String third, String forth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public String getThird() {
        return third;
    }

    public String getForth() {
        return forth;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public void setForth(String forth) {
        this.forth = forth;
    }
}
