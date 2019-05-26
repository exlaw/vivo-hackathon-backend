package vivo.chainpaper.entity;

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
public class StarTest {
    Star star=new Star("1","2");
    Star star2=new Star();

    @Test
    public  void Test1(){
        TestUtil.getProperty(star);
        TestUtil.setProperty(star,"1");
        TestUtil.getProperty(star2);
    }

}