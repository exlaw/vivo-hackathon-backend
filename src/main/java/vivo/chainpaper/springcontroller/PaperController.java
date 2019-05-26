package vivo.chainpaper.springcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vivo.chainpaper.blservice.paper.PaperBlService;
import vivo.chainpaper.dto.Block;
import vivo.chainpaper.entity.Paper;
import vivo.chainpaper.parameters.paper.PaperDraft;
import vivo.chainpaper.parameters.paper.PaperUploadParams;
import vivo.chainpaper.response.Response;
import vivo.chainpaper.response.paper.PaperUploadResponse;
import vivo.chainpaper.util.UserInfoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController(value="/papers")
public class PaperController {
    private final PaperBlService paperService;

    @Autowired
    public PaperController(PaperBlService paperBlService){
        this.paperService=paperBlService;
    }


    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public @ResponseBody PaperUploadResponse
    uploadPaper(@RequestBody PaperUploadParams params, HttpServletResponse response){
        PaperDraft pd=params.getPaperDraft();
        Paper paper=new Paper(pd.getAbstractContent(),pd.getIntroduction(),pd.getContent(),pd.getConclusion(),pd.getReference(), UserInfoUtil.getUsername());
        Block block=paperService.addPaperToChainStore(pd,UserInfoUtil.getUsername());//上链
        paper.setIndex(block.getBlockIndex());
        paper.setOffset(block.getBlockOffset());
        paperService.addPaperToDatabase(paper);
        response.setStatus(200);
        return new PaperUploadResponse(paper.getId());
    }


}
