import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringRunner;

import vivo.chainpaper.MainApplication;
import vivo.chainpaper.parameters.Collabrate.CollabrateResultParam;
import vivo.chainpaper.parameters.paper.PaperDraft;
import vivo.chainpaper.parameters.paper.PaperUploadParams;
import vivo.chainpaper.parameters.paper.Reference;
import vivo.chainpaper.parameters.user.RegisterParams;
import vivo.chainpaper.response.paper.PaperUploadResponse;
import vivo.chainpaper.springcontroller.CollabrateController;
import vivo.chainpaper.springcontroller.PaperController;
import vivo.chainpaper.springcontroller.account.UserController;
import vivo.chainpaper.util.HttpUtil;
import vivo.chainpaper.util.MockNameutil;
import vivo.chainpaper.util.UserInfoUtil;

import javax.transaction.Transactional;



@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class DemoTest {


//    @Before
//    public void setup() {
//        UserInfoUtil.nameUtil = new MockNameutil();
//    }
//
//    @Autowired
//    PaperController paperController;
//
//    @Autowired
//    UserController userController;
//
//    @Autowired
//    CollabrateController collabrateController;
//
//    @Test
//    public void pageTest()  {
//       long Id =  Long.valueOf("155888397592533213");
//       paperController.getComment("155888397592533213");
//       paperController.getPaperInfo(Id);
//       paperController.getPapersInfo();
//       paperController.getScore("155888397592533213");
//       paperController.getStar("155888397592533213");
////       paperController.updateDemo("155888397592533213", new PaperUploadParams());
//       paperController.uploadStar(Id);
//    }
//
//    @Test
//    public void paperTest(){
//        Reference[] refs=new Reference[2];
//        refs[0]=new Reference("published","","","");
//        refs[1]=new Reference("chainpaper","","","");
//        PaperDraft paperDraft=new PaperDraft(refs,"a","a","a","a","a","a","a");
//        PaperUploadParams paperUploadParams=new PaperUploadParams(paperDraft);
//
//        PaperUploadResponse paperUploadResponse=paperController.uploadPaper(paperUploadParams);
//        paperController.updateDemo(paperUploadResponse.getPaperId(),paperUploadParams);
//    }

//    @Test
//    public void userTest() {
//        userController.info("law");
//        userController.login("law", "123456");
//        userController.register(new RegisterParams("law1111", "123456", "", "student"));
//    }
//
//    @Test
//    public void collabrateTest()  {
//        collabrateController.accept("155889531193387641");
//        collabrateController.acceptInvitation("155889531193387641");
//        collabrateController.acceptList("155889531193387641");
//        collabrateController.invitationList("155889531193387641");
//        collabrateController.request(new CollabrateResultParam("155888397592533213"));
//        collabrateController.invitation(new CollabrateResultParam("155888397592533213"));
//    }

    @Test
    public void httpTest()  {
        HttpUtil util = new HttpUtil();
    }



}
