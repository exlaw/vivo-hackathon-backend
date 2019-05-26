package vivo.chainpaper.springcontroller.upload;

import io.swagger.annotations.*;
import vivo.chainpaper.blservice.upload.ImageUploadBlService;
import vivo.chainpaper.dao.account.UserDao;
import vivo.chainpaper.exception.SystemException;
import vivo.chainpaper.response.Response;
import vivo.chainpaper.response.WrongResponseEntity;
import vivo.chainpaper.response.upload.UploadImageResponse;
import vivo.chainpaper.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    private final ImageUploadBlService imageUploadBlService;

    @Autowired
    public UploadController(UserDao userDao, ImageUploadBlService imageUploadBlService) {
        this.imageUploadBlService = imageUploadBlService;
    }


    @ApiOperation(value = "上传图片", notes = "上传图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "multipartFile", value = "图片", required = true, dataType = "MultipartFile")
    })
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Image uploaded", response = UploadImageResponse.class),
            @ApiResponse(code = 403, message = "Upload session timed out", response = WrongResponseEntity.class),
            @ApiResponse(code = 404, message = "Upload session id not exist", response = WrongResponseEntity.class),
            @ApiResponse(code = 503, message = "Failure", response = WrongResponseEntity.class)
    })
    public ResponseEntity<Response> uploadFiles(@RequestParam("file") MultipartFile multipartFile) {
        try {
            return new ResponseEntity<>(imageUploadBlService.uploadFiles(multipartFile), HttpStatus.CREATED);
        } catch (SystemException e) {
            e.printStackTrace();
            return e.getResponse();
        }
    }

    @ApiOperation(value = "上传图片", notes = "上传图片且附加学号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "multipartFile", value = "图片", required = true, dataType = "MultipartFile")
    })
    @RequestMapping(value = "upload/user", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Image uploaded", response = UploadImageResponse.class),
            @ApiResponse(code = 403, message = "Upload session timed out", response = WrongResponseEntity.class),
            @ApiResponse(code = 404, message = "Upload session id not exist", response = WrongResponseEntity.class),
            @ApiResponse(code = 503, message = "Failure", response = WrongResponseEntity.class)
    })
    public ResponseEntity<Response> uploadFilesWithUser(@RequestParam("file") MultipartFile multipartFile) {
        try {
            return new ResponseEntity<>(imageUploadBlService.uploadFilesWithUser(multipartFile, UserInfoUtil.getUsername()), HttpStatus.CREATED);
        } catch (SystemException e) {
            e.printStackTrace();
            return e.getResponse();
        }
    }
}
