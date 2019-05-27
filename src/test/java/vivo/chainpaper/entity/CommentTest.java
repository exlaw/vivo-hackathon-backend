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
public class CommentTest {
    Comment comment=new Comment();
    Comment comment1=new Comment("1","2","3");

    @Test
    public void test1(){
        TestUtil.getProperty(comment1);
        TestUtil.setProperty(comment1,"1");
    }

}