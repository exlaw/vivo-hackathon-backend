package vivo.chainpaper.springcontroller.account;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vivo.chainpaper.bl.account.UserBlServiceImpl;
import vivo.chainpaper.dao.CollabrationDao;
import vivo.chainpaper.dao.PaperDao;
import vivo.chainpaper.dao.account.UserDao;
import vivo.chainpaper.entity.Paper;
import vivo.chainpaper.entity.account.User;
import vivo.chainpaper.exception.UserAlreadyExistsException;
import vivo.chainpaper.exception.UsernameDoesNotFoundException;
import vivo.chainpaper.exception.WrongUsernameOrPasswordException;
import vivo.chainpaper.parameters.user.RegisterParams;
import vivo.chainpaper.response.InfoPersonResponse;
import vivo.chainpaper.response.Response;
import vivo.chainpaper.response.WrongResponseEntity;
import vivo.chainpaper.response.user.LoginResponse;
import vivo.chainpaper.response.user.RegisterResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private final UserBlServiceImpl userBlService;

    private final UserDao userDao;

    private final CollabrationDao collabrationDao;

    private final PaperDao paperDao;

    @Autowired
    public UserController(UserBlServiceImpl userBlService, UserDao userDao, CollabrationDao collabrationDao, PaperDao paperDao) {
        this.userBlService = userBlService;
        this.userDao = userDao;
        this.collabrationDao = collabrationDao;
        this.paperDao = paperDao;
    }

    @ApiOperation(value = "用户登录", notes = "验证用户登录并返回token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = LoginResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponseEntity.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponseEntity.class)})
    @ResponseBody
    public ResponseEntity<Response> login(
            @RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("here");
        try {
            LoginResponse loginResponse = userBlService.login(username, password);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (WrongUsernameOrPasswordException  e) {
            return e.getResponse();
        } catch (UsernameDoesNotFoundException e) {
            return e.getResponse();
        }
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = RegisterResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponseEntity.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponseEntity.class)})
    @ResponseBody
    public ResponseEntity<Response> register(
            @RequestBody RegisterParams params) {
        try {
            RegisterResponse registerResponse = userBlService.register(params);
            return new ResponseEntity<>(registerResponse, HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            return e.getResponse();
        }
    }

    /**
     * 14.	查看个人信息
     *
     * GET /user/{userId}
     *
     * 200: {
     * userId: string;
     * username: string;
     * role: "student" | "teacher";
     * papers: PaperInfo[]; // 自己上传过的所有论文
     * score: number; // 对个人的评分，你们怎么算看你们
     * collabrationInvitationIds: string[]; // 自己接受到的合作邀请的ID
     * collabrationRequestIds: string[]; // 自己发出的合作请求的ID
     * }
     */

    @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response> info(
            @PathVariable("userId") String userId
    ){
        User user = this.userDao.findUserByUsername(userId);

        List<String> paperIds = paperDao.findPapersByWriter(user.getUsername());
        List<String>  collabrationInvitationIds = this.collabrationDao.getByInviter(user.getUsername(), "invitation");
        List<String>  collabrationRequestIds = this.collabrationDao.getByRequester(user.getUsername(), "request");

        List<String>  paperIdsInCollabration = new ArrayList<>();

        List<Paper> papers = this.paperDao.findAll();
        for(Paper paper : papers) {
            if(paper.getCooperator().contains(userId) && paper.getCooperator().size() > 1) {
                paperIdsInCollabration.add(paper.getId());
            }
        }
        InfoPersonResponse response = new InfoPersonResponse(user.getId(), user.getRole(), user.getUsername()
        ,0 , paperIds, collabrationInvitationIds, collabrationRequestIds, paperIdsInCollabration);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
