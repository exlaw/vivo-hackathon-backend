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
public class RegisterResponseTest {
    @Test
    public void name() {
        RegisterResponse res=new RegisterResponse();
        RegisterResponse res1=new RegisterResponse("","");
        TestUtil.getProperty(res);
        TestUtil.setProperty(res1,"");
    }
}