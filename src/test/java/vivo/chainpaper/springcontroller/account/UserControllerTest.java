package vivo.chainpaper.springcontroller.account;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.parameters.user.RegisterParams;
import vivo.chainpaper.springcontroller.CollabrateController;
import vivo.chainpaper.springcontroller.PaperController;
import vivo.chainpaper.util.MockNameutil;
import vivo.chainpaper.util.UserInfoUtil;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class UserControllerTest {
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
    public void userTest() {
            userController.register(new RegisterParams("law", "123456", "", "student"));
            userController.register(new RegisterParams("law", "123456", "", "student"));
        userController.info("law");
        userController.login("law", "123456");
            userController.login("laws", "123456");
            userController.login("law", "123456u77");

    }
}