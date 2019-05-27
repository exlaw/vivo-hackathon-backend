package vivo.chainpaper.response.paper;

import vivo.chainpaper.dto.PaperInfo;
import vivo.chainpaper.response.Response;

public class SinglePaperGetResponse  extends Response {
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
