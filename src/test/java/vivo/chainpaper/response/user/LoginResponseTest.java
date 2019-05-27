package vivo.chainpaper.response.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.TestUtil;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class LoginResponseTest {
    @Test
    public void name() {
        LoginResponse r=new LoginResponse();
        LoginResponse r1=new LoginResponse();
        TestUtil.getProperty(r);
        TestUtil.getProperty(r1);
        TestUtil.setProperty(r1,"1");
    }
}