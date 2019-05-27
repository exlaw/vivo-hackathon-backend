package vivo.chainpaper.response;

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
public class CommentItemTest {
    CommentItem commentItem=new CommentItem("","","");
    @Test
    public void name() {
        TestUtil.getProperty(commentItem);
        TestUtil.setProperty(commentItem,"1");
    }
}