package vivo.chainpaper.parameters.user;

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
public class RegisterParamsTest {
    RegisterParams params=new RegisterParams();
    RegisterParams params2=new RegisterParams("","","","");
    @Test
    public void test(){
        TestUtil.getProperty(params);
        TestUtil.getProperty(params2);
        TestUtil.setProperty(params2,"1");
    }
}