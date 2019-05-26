package vivo.chainpaper.springcontroller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.parameters.paper.*;
import vivo.chainpaper.response.paper.PaperUploadResponse;
import vivo.chainpaper.springcontroller.account.UserController;
import vivo.chainpaper.util.MockNameutil;
import vivo.chainpaper.util.UserInfoUtil;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class PaperControllerTest {
    @Before
    public void setup() {
        UserInfoUtil.nameUtil = new MockNameutil();
    }

    @Autowired
    PaperController paperController;

    @Autowired
    UserController userController;

    @Autowired
    CollabrateController collabrateController;


    @Test
    public void paperTest(){
        Reference[] refs=new Reference[2];
        refs[0]=new Reference("published","","","");
        refs[1]=new Reference("chainpaper","","","");
        PaperDraft paperDraft=new PaperDraft(refs,"a","a","a","a","a","a","a");
        PaperUploadParams paperUploadParams=new PaperUploadParams(paperDraft);

        PaperUploadResponse paperUploadResponse=paperController.uploadPaper(paperUploadParams);
        paperController.updateDemo(paperUploadResponse.getPaperId(),paperUploadParams);
        paperController.uploadStar(paperUploadResponse.getPaperId());
        paperController.uploadStar(paperUploadResponse.getPaperId(),new ScoreParameter(9));
        paperController.getScore(paperUploadResponse.getPaperId());
        paperController.getStar(paperUploadResponse.getPaperId());
        paperController.uploadComment(paperUploadResponse.getPaperId(),new CommentParameter("111hh"));
        paperController.getComment(paperUploadResponse.getPaperId());
        paperController.getPaperInfo(paperUploadResponse.getPaperId());
        paperController.getPapersInfo();
        paperController.getPaperRecommend();
    }
}