package vivo.chainpaper.springcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vivo.chainpaper.blservice.paper.PaperBlService;
import vivo.chainpaper.dao.PaperDao;
import vivo.chainpaper.dto.Block;
import vivo.chainpaper.entity.Paper;
import vivo.chainpaper.parameters.paper.PaperDraft;
import vivo.chainpaper.parameters.paper.PaperUploadParams;
import vivo.chainpaper.response.Response;
import vivo.chainpaper.response.paper.PaperUploadResponse;
import vivo.chainpaper.util.TimeUtil;
import vivo.chainpaper.util.UserInfoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController(value="/papers")
public class PaperController {
    private final PaperBlService paperService;
    private final PaperDao paperDao;

    @Autowired
    public PaperController(PaperBlService paperBlService,PaperDao paperDao){
        this.paperService=paperBlService;
        this.paperDao=paperDao;
    }

    //增加论文
    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public @ResponseBody PaperUploadResponse
    uploadPaper(@RequestBody PaperUploadParams params, HttpServletResponse response){
        PaperDraft pd=params.getPaperDraft();
        Paper paper=new Paper(pd.getAbstractContent(),pd.getIntroduction(),pd.getContent(),pd.getConclusion(),pd.getReference(), UserInfoUtil.getUsername(),pd.getTitle(),pd.getKeywords(),pd.getAcknowledgement(), TimeUtil.getTimeStamp());
        Block block=paperService.addPaperToChainStore(pd,UserInfoUtil.getUsername());//上链
        paper.setIndexs(block.getBlockIndex());
        paper.setOffsets(block.getBlockOffset());
        paperService.addPaperToDatabase(paper);
        response.setStatus(200);

        return new PaperUploadResponse(paper.getId());
    }

    //修改论文
    @RequestMapping(value = "/{paperId}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public @ResponseBody PaperUploadResponse updateDemo(@PathVariable("paperId") long paperId, @RequestBody PaperUploadParams params,
                           HttpServletRequest request, HttpServletResponse response){
        PaperDraft pd=params.getPaperDraft();
        Paper paper=new Paper(pd.getAbstractContent(),pd.getIntroduction(),pd.getContent(),pd.getConclusion(),pd.getReference(), UserInfoUtil.getUsername(),pd.getTitle(),pd.getKeywords(),pd.getAcknowledgement(), TimeUtil.getTimeStamp());
        Paper paper0=paperDao.findById(Long.toString(paperId)).get();
        Block block=paperService.addPaperToChainStore(pd,UserInfoUtil.getUsername());//上链
        paper.setId(paper0.getId());
        paper.setIndexs(block.getBlockIndex());
        paper.setOffsets(block.getBlockOffset());
        paperDao.save(paper);
        response.setStatus(200);
        return new PaperUploadResponse(Long.toString(paperId));
    }

    //查看已经发表的论文列表

}
