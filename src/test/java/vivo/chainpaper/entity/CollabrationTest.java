package vivo.chainpaper.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.TestUtil;

import javax.transaction.Transactional;



@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class CollabrationTest {
    Collabration collabration=new Collabration();
    Collabration collabration2=new Collabration("1","2","3","4","5","6",false);

    @Test
    public void test1(){
        TestUtil.getProperty(collabration);
        TestUtil.getProperty(collabration2);
        TestUtil.setProperty(collabration2,"1");
    }
}