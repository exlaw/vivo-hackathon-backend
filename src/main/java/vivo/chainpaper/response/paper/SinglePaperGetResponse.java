package vivo.chainpaper.response.paper;

import vivo.chainpaper.dto.PaperInfo;

public class SinglePaperGetResponse {
    PaperInfo paper;

    public SinglePaperGetResponse(PaperInfo paper) {
        this.paper = paper;
    }

    public SinglePaperGetResponse() {
    }

    public PaperInfo getPaper() {
        return paper;
    }

    public void setPaper(PaperInfo paper) {
        this.paper = paper;
    }
}
