package vivo.chainpaper.response;

import java.util.List;

public class PaperRefResponse extends Response{

    private PaperRef refs;

    public PaperRefResponse(PaperRef refs) {
        this.refs = refs;
    }

    public PaperRef getRefs() {
        return refs;
    }

    public void setRefs(PaperRef refs) {
        this.refs = refs;
    }
}
