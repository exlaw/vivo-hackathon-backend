package vivo.chainpaper.response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.entity.Comment;

import javax.transaction.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.*;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class CommentResponseTest {

    @Test
    public void name() {
        Comment comment=new Comment();
        ArrayList<Comment> commentArrayList=new ArrayList<>();
        commentArrayList.add(comment);
        CommentResponse commentResponse=new CommentResponse(commentArrayList);
        commentResponse.getComments();
        commentResponse.setComments(null);
    }
}