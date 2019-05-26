package vivo.chainpaper.dto;

public class PaperBlock {
    String uid;
    String abstractContent;
    String introduction;
    String content;
    String conclusion;

    public PaperBlock(String uid, String abstractContent, String introduction, String content, String conclusion) {
        this.uid = uid;
        this.abstractContent = abstractContent;
        this.introduction = introduction;
        this.content = content;
        this.conclusion = conclusion;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
