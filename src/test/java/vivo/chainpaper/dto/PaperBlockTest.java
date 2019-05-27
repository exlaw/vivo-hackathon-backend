package vivo.chainpaper.dto;

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
public class PaperBlockTest {
    @Test
    public void name() {
        PaperBlock paperBlock=new PaperBlock("","","","","","","","");
        TestUtil.getProperty(paperBlock);
        TestUtil.setProperty(paperBlock,"1");
    }
}