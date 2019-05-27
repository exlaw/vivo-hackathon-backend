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
public class ScoreParameterTest {
    ScoreParameter scoreParameter=new ScoreParameter(2);

    @Test
    public void test(){
        TestUtil.getProperty(scoreParameter);
        TestUtil.setProperty(scoreParameter,"2");
    }
}