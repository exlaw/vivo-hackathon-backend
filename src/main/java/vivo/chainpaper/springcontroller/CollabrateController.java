package vivo.chainpaper.springcontroller;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vivo.chainpaper.dao.CollabrationDao;
import vivo.chainpaper.dao.PaperDao;
import vivo.chainpaper.entity.Collabration;
import vivo.chainpaper.entity.Paper;
import vivo.chainpaper.parameters.Collabrate.CollabrateResultParam;
import vivo.chainpaper.response.*;
import vivo.chainpaper.util.TimeUtil;
import vivo.chainpaper.util.UserInfoUtil;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("collabration")
public class CollabrateController {

    /**
     *  10.	 请求合作（其他人给作者发）
     *
     *   POST /collabration/request
     *
     *   body: {
     *      paperId: string;
     *   }
     *
     *   200: { collabrationRequestId: string; }
     */

    private CollabrationDao collabrationDao;

    private PaperDao paperDao;

    @Autowired
    public CollabrateController(CollabrationDao collabrationDao, PaperDao paperDao) {
        this.collabrationDao = collabrationDao;
        this.paperDao = paperDao;
    }

    /**
     * 10.	 请求合作（其他人给作者发）
     *
     * POST /collabration/request
     *
     * body: {
     * paperId: string;
     *       }
     *
     * 200: { collabrationRequestId: string; }
     */
    @RequestMapping(value = "request", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> request(
            @RequestBody CollabrateResultParam resultParam
    ) {
        Collabration collabration = new  Collabration(Long.toString(System.currentTimeMillis())+ new Random().nextInt(100000),
                UserInfoUtil.getUsername(), this.paperDao.findUsernameByPaperId(resultParam.getPaperId()), TimeUtil.getTimeStamp(),resultParam.getPaperId(), "request", false);
        this.collabrationDao.save(collabration);
        RequestResponse requestResponse = new RequestResponse(collabration.getCollabrationID());
        return new ResponseEntity<>(requestResponse, HttpStatus.OK);
    }

    /**
     *  11.	 作者接受合作
     *
     * POST /collabration/request/{paper}
     */
    @RequestMapping(value = "request/{collabrationRequestId}", method = RequestMethod.POST)
    @ResponseBody
    public String accept(
            @PathVariable("collabrationRequestId") String collabrationRequestId
    )  {
        Collabration collabration =  this.collabrationDao.getOne(collabrationRequestId);
        collabration.setState(true);
        this.collabrationDao.save(collabration);
        Paper paper = this.paperDao.getOne(collabration.getPaperId());
        List<String> cooperators = paper.getCooperator();
        cooperators.add(collabration.getFromUsername());
        paper.setCooperator(cooperators);
        this.paperDao.save(paper);
        return collabrationRequestId;
    }

    /**
     *  12.	 作者邀请其他人合作
     *
     * POST /collabration/invitation
     *
     * body: { paperId: string; }
     *
     * 200: { collabrationInvitationId: string; }
     */

    @RequestMapping(value = "invitation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> invitation(
            @RequestBody CollabrateResultParam resultParam
    )  {
        System.out.println(resultParam.getPaperId());
        Collabration collabration = new  Collabration(Long.toString(System.currentTimeMillis())+ new Random().nextInt(100000),
                UserInfoUtil.getUsername(), this.paperDao.findUsernameByPaperId(resultParam.getPaperId()),  TimeUtil.getTimeStamp(), resultParam.getPaperId(),  "invitation", false);
        this.collabrationDao.save(collabration);
        InvitationResponse invitationResponse = new InvitationResponse(collabration.getCollabrationID());
        return new ResponseEntity<>(invitationResponse, HttpStatus.OK);
    }

    /**
     *  13.	其他人同意合作
     *
     *   POST /collabration/invitation/{collabrationInvitationId}
     */

    @RequestMapping(value = "invitation/{collabrationInvitationId}", method = RequestMethod.POST)
    @ResponseBody
    public String acceptInvitation(
            @PathVariable("collabrationInvitationId") String collabrationInvitationId
    ) {

        Collabration collabration =  this.collabrationDao.getOne(collabrationInvitationId);
        collabration.setState(true);
        Paper paper = this.paperDao.getOne(collabration.getPaperId());
        List<String> cooperators = paper.getCooperator();
        cooperators.add(collabration.getToUsername());
        paper.setCooperator(cooperators);
        this.paperDao.save(paper);
        this.collabrationDao.save(collabration);
        return collabrationInvitationId;
    }


    /**
     *  15.	查看合作邀请信息
     *  GET /collabration/invitation/{collabrationInvitationId}
     *
     * 200: {
     * time: string;
     * inviteeId: string; // 被接受的用户ID
     * inviterId: string; // 发出邀请的ID
     * paperId: string; // 要一起合作的文章ID
     * }
     */

    @RequestMapping(value = "invitation/{collabrationInvitationId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response> invitationList(
            @PathVariable("collabrationInvitationId") String collabrationInvitationId
    ) {
        Collabration collabration = this.collabrationDao.getOne(collabrationInvitationId);
        InfoInvitationResponce response = new InfoInvitationResponce( collabration.getTime(),
                collabration.getToUsername(), collabration.getFromUsername(), collabration.getPaperId(),collabration.getCollabrationID());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 16.	查看合作请求信息
     * GET /collabration/request/{collabrationInvitationId}
     *
     * 200: {
     * time: string;
     * requesteeId: string; // 被请求用户的ID
     * requesterId: string; // 请求发起的用户ID
     * paperId: string; // 要一起合作的文章ID
     * }
     */

    @RequestMapping(value = "request/{collabrationRequestId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response> acceptList(
            @PathVariable("collabrationRequestId") String collabrationRequestId
    ) {
        Collabration collabration = this.collabrationDao.getOne(collabrationRequestId);
        InfoRequestResponce response = new InfoRequestResponce(collabration.getTime(),
                collabration.getToUsername(),  collabration.getFromUsername(), collabration.getPaperId(),collabration.getCollabrationID());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
