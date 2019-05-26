package vivo.chainpaper.parameters.paper;

public class Reference {
    String type;
    String doi;
    String paperId;

    public Reference(String type, String doi, String paperId) {
        this.type = type;
        this.doi = doi;
        this.paperId = paperId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
}
