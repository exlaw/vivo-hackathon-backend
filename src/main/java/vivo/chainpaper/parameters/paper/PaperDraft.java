package vivo.chainpaper.parameters.paper;

public class PaperDraft {
    Reference refs[];
    String abstractContent;
    String introduction;
    String content;
    String conclusion;
    String title;
    String keywords;
    String acknowledgement;

    public PaperDraft(Reference[] reference, String abstractContent, String introduction, String content, String conclusion, String title, String keywords, String acknowledgement) {
        this.refs = reference;
        this.abstractContent = abstractContent;
        this.introduction = introduction;
        this.content = content;
        this.conclusion = conclusion;
        this.title = title;
        this.keywords = keywords;
        this.acknowledgement = acknowledgement;
    }

    public Reference[] getReference() {
        return refs;
    }

    public void setReference(Reference[] reference) {
        this.refs = reference;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAcknowledgement() {
        return acknowledgement;
    }

    public void setAcknowledgement(String acknowledgement) {
        this.acknowledgement = acknowledgement;
    }

    public Reference[] getRefs() {
        return refs;
    }

    public void setRefs(Reference[] refs) {
        this.refs = refs;
    }
}
