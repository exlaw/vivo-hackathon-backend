package vivo.chainpaper.response.paper;

public class PaperUploadResponse {
    String paperId;

    public PaperUploadResponse(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
}
