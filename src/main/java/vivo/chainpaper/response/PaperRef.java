package vivo.chainpaper.response;

import java.util.List;

public class PaperRef {

    private String type;
    private String doi;
    private String paperId;
    private String title;
    private List<PaperRef> refs;

    public PaperRef(String type) {
        this.type = type;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PaperRef> getRefs() {
        return refs;
    }

    public void setRefs(List<PaperRef> refs) {
        this.refs = refs;
    }
}
