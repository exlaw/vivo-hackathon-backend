package vivo.chainpaper.springcontroller.account;

import io.swagger.annotations.*;
import vivo.chainpaper.blservice.account.UserBlService;
import vivo.chainpaper.exception.*;
import vivo.chainpaper.parameters.user.EmailValidationParams;
import vivo.chainpaper.parameters.user.RegisterParams;
import vivo.chainpaper.parameters.user.UserInfoParams;
import vivo.chainpaper.response.Response;
import vivo.chainpaper.response.SuccessResponse;
import vivo.chainpaper.response.WrongResponseEntity;
import vivo.chainpaper.response.user.EmailValidationRequestResponse;
import vivo.chainpaper.response.user.LoginResponse;
import vivo.chainpaper.response.user.RegisterResponse;
import vivo.chainpaper.response.user.UserInfoResponse;
import vivo.chainpaper.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserBlService userBlService;

    @Autowired
    public UserController(UserBlService userBlService) {
        this.userBlService = userBlService;
    }

    @ApiOperation(value = "用户登录", notes = "验证用户登录并返回token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "user", method = RequestMethod.GET)
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
        } catch (WrongUsernameOrPasswordException e) {
            e.printStackTrace();
            return e.getResponse();
        } catch (CannotRegisterException e) {
            e.printStackTrace();
            return e.getResponse();
        } catch (UsernameDoesNotFoundException e) {
            e.printStackTrace();
            return e.getResponse();
        }
    }

    @ApiOperation(value = "用户注册", notes = "管理员注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "user", method = RequestMethod.POST)
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
            e.printStackTrace();
            return e.getResponse();
        }
    }

    @ApiOperation(value = "用户注销", notes = "用户注销")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "user", method = RequestMethod.DELETE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = SuccessResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponseEntity.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponseEntity.class)})
    @ResponseBody
    public ResponseEntity<Response> logoff(@RequestParam("username") String username) {
        try {
            userBlService.logoff(username);
            return new ResponseEntity<>(new SuccessResponse(), HttpStatus.OK);
        } catch (UsernameDoesNotFoundException e) {
            e.printStackTrace();
            return e.getResponse();
        }
    }

    @ApiOperation(value = "请求验证码", notes = "请求发送邮件验证码")
    @RequestMapping(value = "user/validation/email", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EmailValidationRequestResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponseEntity.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponseEntity.class)})
    @ResponseBody
    public ResponseEntity<Response> requestEmailValidation() {
        String username = UserInfoUtil.getUsername();
        try {
            EmailValidationRequestResponse emailValidationRequestResponse = userBlService.requestEmailValidation(username);
            return new ResponseEntity<>(emailValidationRequestResponse, HttpStatus.OK);
        } catch (UsernameDoesNotFoundException e) {
            e.printStackTrace();
            return e.getResponse();
        } catch (InvalidEmailAddressesException e) {
            e.printStackTrace();
            return e.getResponse();
        }
    }

    @ApiOperation(value = "验证码验证", notes = "验证验证码是否正确")
    @RequestMapping(value = "user/validation/email", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponseEntity.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponseEntity.class)})
    @ResponseBody
    public ResponseEntity<Response> validateEmail(@RequestBody EmailValidationParams params) {
        String username = UserInfoUtil.getUsername();
        try {
            userBlService.validateEmail(username, params);
            return new ResponseEntity<>(new SuccessResponse(), HttpStatus.OK);
        } catch (UsernameDoesNotFoundException e) {
            e.printStackTrace();
            return e.getResponse();
        } catch (InvalidCodeException e) {
            e.printStackTrace();
            return e.getResponse();
        }
    }

    @ApiOperation(value = "个人信息", notes = "获得用户个人信息")
    @RequestMapping(value = "user/info", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserInfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponseEntity.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponseEntity.class)})
    @ResponseBody
    public ResponseEntity<Response> getUserInfo() {
        String username = UserInfoUtil.getUsername();
        try {
            UserInfoResponse userInfoResponse = userBlService.getUserInfo(username);
            return new ResponseEntity<>(userInfoResponse, HttpStatus.OK);
        } catch (UsernameDoesNotFoundException e) {
            e.printStackTrace();
            return e.getResponse();
        }
    }

    @ApiOperation(value = "个人信息", notes = "修改用户个人信息")
    @RequestMapping(value = "user/info", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = SuccessResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponseEntity.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponseEntity.class)})
    @ResponseBody
    public ResponseEntity<Response> postUserInfo(@RequestBody UserInfoParams userInfoParams) {
        String username = UserInfoUtil.getUsername();
        return new ResponseEntity<>(userBlService.postUserInfo(userInfoParams, username), HttpStatus.OK);
    }

}
