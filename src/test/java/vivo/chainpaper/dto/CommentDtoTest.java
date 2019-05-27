package vivo.chainpaper.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.TestUtil;
import vivo.chainpaper.entity.Comment;
import vivo.chainpaper.entity.CommentTest;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class CommentDtoTest {
    @Test
    public void name() {
        CommentDto d=new CommentDto();
        CommentDto d1=new CommentDto("","","");
        TestUtil.getProperty(d);
        TestUtil.setProperty(d1,"");
    }
}