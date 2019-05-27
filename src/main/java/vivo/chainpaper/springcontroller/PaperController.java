package vivo.chainpaper.springcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vivo.chainpaper.blservice.paper.PaperBlService;
import vivo.chainpaper.dao.CommentDao;
import vivo.chainpaper.dao.PaperDao;
import vivo.chainpaper.dao.StarDao;
import vivo.chainpaper.dto.CommentDto;
import vivo.chainpaper.dto.PaperInfo;
import vivo.chainpaper.entity.Comment;
import vivo.chainpaper.entity.Paper;
import vivo.chainpaper.entity.Star;
import vivo.chainpaper.parameters.paper.*;
import vivo.chainpaper.response.*;
import vivo.chainpaper.response.paper.ListPaperGetResponse;
import vivo.chainpaper.response.paper.PaperUploadResponse;
import vivo.chainpaper.response.paper.SinglePaperGetResponse;
import vivo.chainpaper.util.TimeUtil;
import vivo.chainpaper.util.UserInfoUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        Paper paper=new Paper(pd.getAbstractContent(),pd.getIntroduction(),pd.getContent(),pd.getConclusion(),pd.getReference(), UserInfoUtil.getUsername(),pd.getTitle(),pd.getKeywords(),pd.getAcknowledgement(), TimeUtil.getTimeStamp());
//        Block block=paperService.addPaperToChainStore(pd,UserInfoUtil.getUsername());//上链
//        paper.setIndexs(block.getBlockIndex());
//        paper.setOffsets(block.getBlockOffset());
        setRefs(paper,pd);
        paperService.addPaperToDatabase(paper);
        return new PaperUploadResponse(paper.getId());
    }

    /**
     * 21.	递归获得某一文章的引用
     * GET /papers/{paperId}/refs
     *
     * type PaperRef={
     * type: "published" | "chainpaper",
     * doi: string;
     * paperId: string;
     * title: string;
     * refSs:PaperRef[];
     * }
     *
     * 200: {
     * refs:PaperRef[]
     * }
     * @param paperId
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/{paperId}/refs", method = RequestMethod.GET,
            consumes = {"*/*"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Response>
    getRefs(@PathVariable("paperId") String paperId){
        Paper paper1 = this.paperDao.getOne(paperId);
        paper1.setTitle(paper1.getTitle());
        PaperRef paperRef = new PaperRef("chainpaper");
        paperRef.setPaperId(paperId);
        paperRef.setTitle(paper1.getTitle());
        paperRef.setRefs(this.getRefsByID(paperId));
        return new ResponseEntity<>(new PaperRefResponse(paperRef), HttpStatus.OK);
    }

    private String published = "published";
    public List<PaperRef> getRefsByID(String paperId){

        List<PaperRef> refs = new ArrayList<>();
        Paper paper = this.paperDao.getOne(paperId);
        for(int i = 0; i < paper.getRefs().size() ;  i++){
            String pID = paper.getRefs().get(i).split("###")[0];
            PaperRef paperRef = new PaperRef(paper.getReferenceType().get(i));
            if(paper.getReferenceType().get(i).equals(this.published)){
                paperRef.setDoi(pID);
                paperRef.setRefs(new ArrayList<>());
            }else{
                paperRef.setPaperId(pID);
                Paper paper1 = this.paperDao.getOne(pID);
                paperRef.setTitle(paper1.getTitle());
                paperRef.setRefs(this.getRefsByID(pID));
            }
            refs.add(paperRef);
        }
        return refs;

    }




    //修改论文
    @RequestMapping(value = "/{paperId}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public @ResponseBody PaperUploadResponse updateDemo(@PathVariable("paperId") String paperId, @RequestBody PaperUploadParams params){
        Paper origin = this.paperDao.getOne(paperId);
        PaperDraft pd=params.getPaperDraft();
        Paper paper=new Paper(pd.getAbstractContent(),pd.getIntroduction(),pd.getContent(),pd.getConclusion(),pd.getReference(), UserInfoUtil.getUsername(),pd.getTitle(),pd.getKeywords(),pd.getAcknowledgement(), TimeUtil.getTimeStamp());
//        Paper paper0=paperDao.findById(Long.toString(paperId)).get();
//        Block block=paperService.addPaperToChainStore(pd,UserInfoUtil.getUsername());//上链
//        paper.setId(paper0.getId());
//        paper.setIndexs(block.getBlockIndex());
//        paper.setOffsets(block.getBlockOffset());
        paper.setId(paperId);
        Paper paper1 = setRefs(paper, pd);
        paper1.setCooperator(origin.getCooperator());
        paperDao.save(paper1);
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

    @RequestMapping(value = "/recommendation",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    ResponseEntity<Response> getPaperRecommend(){
        ArrayList<Paper> papers=(ArrayList<Paper>)paperDao.findAll();
        if(papers.isEmpty()){
            return new ResponseEntity<>(new SinglePaperGetResponse(null), HttpStatus.OK);
        }else {
            int index= new Random().nextInt(papers.size());
            PaperInfo paperInfo=getPaperInfoFromId(papers.get(index).getId());
            return new ResponseEntity<>(new SinglePaperGetResponse(paperInfo), HttpStatus.OK);
        }

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
        paperInfo.setUploadTime(paper.getcTime());
        paperInfo.setState(paper.getPaperState());
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
            if(paper.getReferenceType().get(i).equals(this.published)) {
                ref = new Reference(this.published ,info1,null,info2);
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
            dto.setTime(comment.getTimeStamp());
            dto.setUserId(comment.getUserId());
            commentDtos[commentCount]=dto;
            commentCount++;
        }
        paperInfo.setComments(commentDtos);
        return paperInfo;
    }

    private Paper setRefs(Paper paper,PaperDraft pd){
        ArrayList<String> refStrs=new ArrayList<>();
        ArrayList<String> type=new ArrayList<>();
        for(Reference ref:pd.getReference()){
            String refType = ref.getType();
            if(refType.equals("published")){
                refStrs.add(ref.getDoi()+"###"+ref.getContext());
            }else{
                refStrs.add(ref.getPaperId()+"###"+ref.getContext());
            }
            type.add(refType);
        }
        paper.setRefs(refStrs);
        paper.setReferenceType(type);
        return paper;
    }

}
