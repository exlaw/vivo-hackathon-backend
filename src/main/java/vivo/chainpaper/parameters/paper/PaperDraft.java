package vivo.chainpaper.parameters.paper;

public class PaperDraft {
    Reference reference[];
    String abstractContent;
    String introduction;
    String content;
    String conclusion;

    public PaperDraft(Reference[] reference, String abstractContent, String introduction, String content, String conclusion) {
        this.reference = reference;
        this.abstractContent = abstractContent;
        this.introduction = introduction;
        this.content = content;
        this.conclusion = conclusion;
    }

    public Reference[] getReference() {
        return reference;
    }

    public void setReference(Reference[] reference) {
        this.reference = reference;
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
}
