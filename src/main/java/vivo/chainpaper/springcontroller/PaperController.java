package vivo.chainpaper.springcontroller;


import javafx.print.PaperSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vivo.chainpaper.blservice.paper.PaperBlService;
import vivo.chainpaper.dao.CommentDao;
import vivo.chainpaper.dao.PaperDao;
import vivo.chainpaper.dao.StarDao;
import vivo.chainpaper.dto.Block;
import vivo.chainpaper.dto.CommentDto;
import vivo.chainpaper.dto.PaperInfo;
import vivo.chainpaper.entity.Comment;
import vivo.chainpaper.entity.Paper;
import vivo.chainpaper.entity.Star;
import vivo.chainpaper.entity.account.User;
import vivo.chainpaper.parameters.paper.*;
import vivo.chainpaper.response.Response;
import vivo.chainpaper.response.paper.ListPaperGetResponse;
import vivo.chainpaper.response.paper.PaperUploadResponse;
import vivo.chainpaper.response.paper.SinglePaperGetResponse;
import vivo.chainpaper.util.TimeUtil;
import vivo.chainpaper.util.UserInfoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController(value="/papers")
public class PaperController {
    private final PaperBlService paperService;
    private final PaperDao paperDao;
    private final StarDao starDao;
    private final CommentDao commentDao;

    @Autowired
    public PaperController(PaperBlService paperBlService,PaperDao paperDao,StarDao starDao,CommentDao commentDao){
        this.paperService=paperBlService;
        this.paperDao=paperDao;
        this.starDao=starDao;
        this.commentDao=commentDao;
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

        response.setStatus(200);
        setRefs(paper,pd);
        paperService.addPaperToDatabase(paper);
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
        setRefs(paper,pd);
        paperDao.save(paper);
        response.setStatus(200);
        return new PaperUploadResponse(Long.toString(paperId));
    }

    //查看已经发表的论文列表

    //点赞
    @RequestMapping(value = "/{paperId}/star", method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void
    uploadStar(@PathVariable("paperId") long paperId, HttpServletResponse response){
       boolean isExited=starDao.existsById(UserInfoUtil.getUsername()+Long.toString(paperId));
        Star star;
       if(isExited){
           star=starDao.findById(Long.toString(paperId)).get();


       }else{
            star=new Star(UserInfoUtil.getUsername(),Long.toString(paperId));

       }
       star.setStar(1);
       starDao.save(star);
       response.setStatus(200);
    }


    //打分
    @RequestMapping(value = "/{paperId}/score", method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void
    uploadStar(@PathVariable("paperId") long paperId, @RequestBody ScoreParameter params, HttpServletResponse response){
        String uid=UserInfoUtil.getUsername();
        boolean isExited=starDao.existsById(uid+Long.toString(paperId));
        Star star;
        if(isExited){
            star=starDao.findById(Long.toString(paperId)).get();
        }else{
            star=new Star(uid,Long.toString(paperId));
        }
        star.setScore(params.getScore());
        starDao.save(star);
        response.setStatus(200);
    }

    //评论
    @RequestMapping(value = "/{paperId}/comment", method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void
    uploadComment(@PathVariable("paperId") long paperId, @RequestBody CommentParameter params, HttpServletResponse response){
        Comment comment=new Comment(UserInfoUtil.getUsername(),Long.toString(paperId),params.getComment());
        commentDao.save(comment);
        response.setStatus(200);
    }

    @RequestMapping(value = "/{paperId}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    SinglePaperGetResponse getPaperInfo(@PathVariable("paperId") long paperId, HttpServletRequest request, HttpServletResponse response){
        PaperInfo paperInfo=getPaperInfoFromId(paperId);
        response.setStatus(200);
        return new SinglePaperGetResponse(paperInfo);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    ListPaperGetResponse getPapersInfo(HttpServletRequest request, HttpServletResponse response){
        ArrayList<Paper> papers=(ArrayList<Paper>)paperDao.findAll();
        PaperInfo[] paperInfos=new PaperInfo[papers.size()];
        for(int i=0;i<paperInfos.length;i++){
            paperInfos[i]=getPaperInfoFromId(Long.parseLong(papers.get(i).getId()));
        }
        return new ListPaperGetResponse(paperInfos);
    }


    private PaperInfo getPaperInfoFromId(long paperId){
        Paper paper=paperDao.findById(Long.toString(paperId)).get();
        ArrayList<String> names=(ArrayList<String>) paper.getCooperator();
        String[] authors=new String[names.size()];
        names.toArray(authors);
        PaperInfo paperInfo=new PaperInfo();
        paperInfo.setAuthors(authors);
        paperInfo.setPaperId(Long.toString(paperId));
        paperInfo.setUploadTime(paper.getC_time());
        paperInfo.setState(paper.getPaper_state());
        Reference[] references=new Reference[paper.getRefs().size()];
        for(int i=0;i<paper.getRefs().size();i++){
            Reference ref;
            if(paper.getReference_type().get(i).equals("published")) {
                ref = new Reference("published",paper.getRefs().get(i).split("###")[0],null,paper.getRefs().get(i).split("###")[1]);
            }else{
                 ref = new Reference("chainpaper",null,paper.getRefs().get(i).split("###")[0],paper.getRefs().get(i).split("###")[1]);
            }
            references[i]=ref;
        }
        PaperDraft pd=new PaperDraft(references,paper.getAbstractContent(),paper.getIntroduction(),paper.getContent(),paper.getConclusion(),paper.getTitle(),paper.getKeywords(),paper.getAcknowledgement());
        paperInfo.setPaper(pd);
        ArrayList<Star> stars=(ArrayList<Star>)starDao.findStarsByPaperId(Long.toString(paperId));
        int starNumber=0;
        double totalScore=0;
        int scoreCount=0;
        for(Star star:stars){
            if(star.getStar()==1){
                starNumber++;
            }
            if(star.getScore()>0){
                scoreCount++;
                totalScore+=star.getScore();
            }
        }
        double avgScore;
        if(scoreCount!=0) {
            avgScore= totalScore / scoreCount;
        }else{
            avgScore=0;
        }
        paperInfo.setScore((int)avgScore);
        paperInfo.setStars(starNumber);

        ArrayList<Comment> comments=(ArrayList<Comment>)commentDao.findCommentsByPaperId(Long.toString(paperId));
        CommentDto[] commentDtos= new CommentDto[comments.size()];
        int commentCount=0;
        for(Comment comment:comments){
            CommentDto dto=new CommentDto();
            dto.setContent(comment.getComment());
            dto.setTime(comment.getTime_stamp());
            dto.setUserId(comment.getUserId());
            commentDtos[commentCount]=dto;
            commentCount++;
        }
        paperInfo.setComments(commentDtos);
        return paperInfo;
    }

    private Paper setRefs(Paper paper,PaperDraft pd){
        ArrayList<String> ref_strs=new ArrayList<>();
        ArrayList<String> type=new ArrayList<>();
        for(Reference ref:pd.getReference()){
            String ref_type=ref.getType();
            if(ref_type.equals("published")){
                ref_strs.add(ref.getDoi()+"###"+ref.getContext());
            }else{
                ref_strs.add(ref.getPaperId()+"###"+ref.getContext());
            }
            type.add(ref_type);
        }
        paper.setRefs(ref_strs);
        paper.setReference_type(type);
        return paper;
    }

}
