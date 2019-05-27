package vivo.chainpaper.response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class ScoreResponseTest {
    @Test
    public void name() {
        ScoreResponse res=new ScoreResponse(1,0);
        res.getMyScore();
        res.getScore();
        res.setScore(1);
        res.setMyScore(1);
    }
}