package vivo.chainpaper.parameters.paper;

public class Reference {
    String type;
    String doi;
    String paperId;
    String context;

    public Reference(String type, String doi, String paperId,String context) {
        this.type = type;
        this.doi = doi;
        this.paperId = paperId;
        this.context=context;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
