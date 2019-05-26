package vivo.chainpaper.response.paper;

import vivo.chainpaper.dto.PaperInfo;

public class ListPaperGetResponse {
    PaperInfo[] papers;

    public ListPaperGetResponse(PaperInfo[] papers) {
        this.papers = papers;
    }

    public PaperInfo[] getPapers() {
        return papers;
    }

    public void setPapers(PaperInfo[] papers) {
        this.papers = papers;
    }

    public ListPaperGetResponse() {
    }
}
