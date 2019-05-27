package vivo.chainpaper.parameters.Collabrate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.TestUtil;
import vivo.chainpaper.entity.Collabration;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class CollabrateResultParamTest {
    CollabrateResultParam param=new CollabrateResultParam("");
    @Test
    public void test(){
        TestUtil.getProperty(param);
        TestUtil.setProperty(param,"1");
    }

}