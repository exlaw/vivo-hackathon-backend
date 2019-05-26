package vivo.chainpaper.parameters.paper;

public class PaperUploadParams {
    PaperDraft paperDraft;

    public PaperDraft getPaperDraft() {
        return paperDraft;
    }

    public void setPaperDraft(PaperDraft paperDraft) {
        this.paperDraft = paperDraft;
    }

    public PaperUploadParams(PaperDraft paperDraft) {
        this.paperDraft = paperDraft;
    }
}
