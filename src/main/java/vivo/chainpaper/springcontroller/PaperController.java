package vivo.chainpaper.springcontroller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vivo.chainpaper.blservice.paper.PaperBlService;
import vivo.chainpaper.response.Response;

@RestController()
public class PaperController {
    private final PaperBlService paperService;

    @Autowired
    public PaperController(PaperBlService paperBlService){
        this.paperService=paperBlService;
    }

    @ApiOperation(value = "上传paper", notes = "用户上传论文")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "papers", method = RequestMethod.POST)
    public ResponseEntity<Response> uploadPaper(){
        return null;
    }


}
