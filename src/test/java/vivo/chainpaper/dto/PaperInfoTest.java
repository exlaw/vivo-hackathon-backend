package vivo.chainpaper.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;

import javax.transaction.Transactional;


@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class PaperInfoTest {

    @Test
    public void test(){
        PaperInfo paperInfo=new PaperInfo();
        paperInfo.setComments(null);
        paperInfo.setStars(1);
        paperInfo.setScore(1);
        paperInfo.setPaper(null);
        paperInfo.setState(0);
        paperInfo.setUploadTime("11");
        paperInfo.setPaperId("1");
        paperInfo.setAuthors(null);

        paperInfo.getComments();
        paperInfo.getStars();
        paperInfo.getScore();
        paperInfo.getPaper();
        paperInfo.getState();
        paperInfo.getUploadTime();
        paperInfo.getPaperId();
        paperInfo.getAuthors();

    }
}