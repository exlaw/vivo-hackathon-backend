package vivo.chainpaper.parameters.paper;

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
public class CommentParameterTest {
    CommentParameter commentParameter=new CommentParameter();
    CommentParameter commentParameter1=new CommentParameter("1");
    @Test
    public void test(){
        TestUtil.getProperty(commentParameter1);
        TestUtil.setProperty(commentParameter1,"00");
    }
}