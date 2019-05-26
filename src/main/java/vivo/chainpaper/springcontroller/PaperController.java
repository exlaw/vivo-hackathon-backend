package vivo.chainpaper.springcontroller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import vivo.chainpaper.parameters.paper.*;
import vivo.chainpaper.response.CommentResponse;
import vivo.chainpaper.response.Response;
import vivo.chainpaper.response.ScoreResponse;
import vivo.chainpaper.response.StarResponse;
import vivo.chainpaper.response.paper.ListPaperGetResponse;
import vivo.chainpaper.response.paper.PaperUploadResponse;
import vivo.chainpaper.response.paper.SinglePaperGetResponse;
import vivo.chainpaper.util.TimeUtil;
import vivo.chainpaper.util.UserInfoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/papers")
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
    uploadPaper(@RequestBody PaperUploadParams params){
        PaperDraft pd=params.getPaperDraft();
        System.out.println(new Gson().toJson(pd));
        Paper paper=new Paper(pd.getAbstractContent(),pd.getIntroduction(),pd.getContent(),pd.getConclusion(),pd.getReference(), UserInfoUtil.getUsername(),pd.getTitle(),pd.getKeywords(),pd.getAcknowledgement(), TimeUtil.getTimeStamp());
//        Block block=paperService.addPaperToChainStore(pd,UserInfoUtil.getUsername());//上链
//        paper.setIndexs(block.getBlockIndex());
//        paper.setOffsets(block.getBlockOffset());
        setRefs(paper,pd);
        paperService.addPaperToDatabase(paper);
        return new PaperUploadResponse(paper.getId());
    }

    //修改论文
    @RequestMapping(value = "/{paperId}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public @ResponseBody PaperUploadResponse updateDemo(@PathVariable("paperId") String paperId, @RequestBody PaperUploadParams params){
//        System.out.println(new Gson());
        PaperDraft pd=params.getPaperDraft();
        Paper paper=new Paper(pd.getAbstractContent(),pd.getIntroduction(),pd.getContent(),pd.getConclusion(),pd.getReference(), UserInfoUtil.getUsername(),pd.getTitle(),pd.getKeywords(),pd.getAcknowledgement(), TimeUtil.getTimeStamp());
//        Paper paper0=paperDao.findById(Long.toString(paperId)).get();
//        Block block=paperService.addPaperToChainStore(pd,UserInfoUtil.getUsername());//上链
//        paper.setId(paper0.getId());
//        paper.setIndexs(block.getBlockIndex());
//        paper.setOffsets(block.getBlockOffset());
        paper.setId(paperId);
        paper=setRefs(paper,pd);
        paperDao.save(paper);
        return new PaperUploadResponse(paperId);
    }

    //查看已经发表的论文列表

    //点赞
    @RequestMapping(value = "/{paperId}/star", method = RequestMethod.POST)
    public void
    uploadStar(@PathVariable("paperId") String paperId){
        String id = UserInfoUtil.getUsername()+paperId;
       boolean isExited=starDao.existsById(id);
        Star star;
       if(isExited){
           star=starDao.findById(id).get();
           star.setStar(star.getStar() == 0?1:0);
       }else{
            star=new Star(UserInfoUtil.getUsername(),paperId);
            star.setStar(1);
       }

       starDao.save(star);
    }

    /**
     *  18.	 获得文章现在的星的数量，以及自己是否已经打过星
     *
     * GET /papers/{paperId}/star
     *
     * 200: {
     * stars: number;
     * stared: boolean;
     * }
     */

    @ResponseBody
    @RequestMapping(value = "/{paperId}/star", method = RequestMethod.GET,
            consumes = {"*/*"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Response>
    getStar(@PathVariable("paperId") String paperId){
        String user = UserInfoUtil.getUsername();
        List<String> users = this.starDao.findStarByPaperId(paperId);
        int stars = users.size();
        boolean stared = users.contains(user);
        return new ResponseEntity<>(new StarResponse(stars, stared), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{paperId}/score", method = RequestMethod.GET,
            consumes = {"*/*"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Response>
    getScore(@PathVariable("paperId") String paperId){
        String user = UserInfoUtil.getUsername();
        List<Integer> scores = this.starDao.findScoreByPaperId(paperId);
        int score = 0;
        int myScore = 0;
        if(!scores.isEmpty()){
            for(int s : scores){
                score += s;
            }
            score/=scores.size();
            try {
                myScore = this.starDao.check(paperId, user);
            }catch (Exception e){
                myScore = 0;
            }
        }
        return new ResponseEntity<>(new ScoreResponse(score, myScore), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{paperId}/comment", method = RequestMethod.GET,
            consumes = {"*/*"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Response>
    getComment(@PathVariable("paperId") String paperId){
        List<Comment> comments = this.commentDao.findCommentsByPaperId(paperId);
        return new ResponseEntity<>(new CommentResponse(comments), HttpStatus.OK);
    }


    //打分

    @RequestMapping(value = "/{paperId}/score", method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void
    uploadStar(@PathVariable("paperId") String paperId, @RequestBody ScoreParameter params){

        String id=UserInfoUtil.getUsername() + paperId;
        boolean isExited=starDao.existsById(id);
        Star star;
        if(isExited){
            star=starDao.findById(id).get();
        }else{
            star=new Star(id,paperId);
        }
        star.setScore(params.getScore());
        starDao.save(star);
    }

    //评论
    @RequestMapping(value = "/{paperId}/comment", method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void
    uploadComment(@PathVariable("paperId") String paperId, @RequestBody CommentParameter params){
        Comment comment=new Comment(UserInfoUtil.getUsername(),paperId,params.getComment());
        commentDao.save(comment);
    }

    @RequestMapping(value = "/{paperId}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody ResponseEntity<Response>
     getPaperInfo(@PathVariable("paperId") String paperId){
        PaperInfo paperInfo=getPaperInfoFromId(paperId);
        return new ResponseEntity<>(new SinglePaperGetResponse(paperInfo), HttpStatus.OK);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    ListPaperGetResponse getPapersInfo(){
        ArrayList<Paper> papers=(ArrayList<Paper>)paperDao.findAll();
        PaperInfo[] paperInfos=new PaperInfo[papers.size()];
        for(int i=0;i<paperInfos.length;i++){
            paperInfos[i]=getPaperInfoFromId(papers.get(i).getId());
        }
        return new ListPaperGetResponse(paperInfos);
    }


    private PaperInfo getPaperInfoFromId(String paperId){
        Paper paper;
        if(paperDao.findById(paperId).isPresent()) {
            paper = paperDao.findById(paperId).get();
        }else{
            return null;
        }
        List<String> names = paper.getCooperator();
        String[] authors=new String[names.size()];
        names.toArray(authors);
        PaperInfo paperInfo=new PaperInfo();
        paperInfo.setAuthors(authors);
        paperInfo.setPaperId(paperId);
        paperInfo.setUploadTime(paper.getC_time());
        paperInfo.setState(paper.getPaper_state());
        Reference[] references=new Reference[paper.getRefs().size()];
        for(int i=0;i<paper.getRefs().size();i++){
            Reference ref;
            String[] infos=paper.getRefs().get(i).split("###");
            String info1="";
            String info2="";
            if(infos.length==2){
                info2=infos[1];
                info1=infos[0];
            }else if(infos.length==1){
                info1=infos[0];
            }
            if(paper.getReference_type().get(i).equals("published")) {
                ref = new Reference("published",info1,null,info2);
            }else{
                 ref = new Reference("chainpaper",null,info1,info2);
            }
            references[i]=ref;
        }
        PaperDraft pd=new PaperDraft(references,paper.getAbstractContent(),paper.getIntroduction(),paper.getContent(),paper.getConclusion(),paper.getTitle(),paper.getKeywords(),paper.getAcknowledgement());
        paperInfo.setPaper(pd);
        ArrayList<Star> stars=(ArrayList<Star>)starDao.findStarsByPaperId(paperId);
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

        ArrayList<Comment> comments=(ArrayList<Comment>)commentDao.findCommentsByPaperId(paperId);
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
