package vivo.chainpaper.springcontroller.account;

import io.swagger.annotations.*;
import vivo.chainpaper.bl.account.UserBlServiceImpl;
import vivo.chainpaper.dao.CollabrationDao;
import vivo.chainpaper.dao.account.UserDao;
import vivo.chainpaper.entity.account.User;
import vivo.chainpaper.exception.*;
import vivo.chainpaper.parameters.user.RegisterParams;
import vivo.chainpaper.response.InfoPersonResponse;
import vivo.chainpaper.response.Response;
import vivo.chainpaper.response.WrongResponseEntity;
import vivo.chainpaper.response.user.LoginResponse;
import vivo.chainpaper.response.user.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private final UserBlServiceImpl userBlService;

    private final UserDao userDao;

    private final CollabrationDao collabrationDao;

    @Autowired
    public UserController(UserBlServiceImpl userBlService, UserDao userDao, CollabrationDao collabrationDao) {
        this.userBlService = userBlService;
        this.userDao = userDao;
        this.collabrationDao = collabrationDao;

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
        List<String> papers = new ArrayList<>();
        List<String>  collabrationInvitationIds = this.collabrationDao.getByRequester(user.getUsername(), "request");
        List<String>  collabrationRequestIds = this.collabrationDao.getByInviter(user.getUsername(), "invitation");

        InfoPersonResponse response = new InfoPersonResponse(user.getId(), user.getUsername(), user.getRole()
        ,0 , papers, collabrationInvitationIds, collabrationRequestIds);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
