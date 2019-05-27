package vivo.chainpaper.parameters.paper;

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
public class PaperUploadParamsTest {
    PaperUploadParams paperUploadParams=new PaperUploadParams(null);

    @Test()
    public void test(){
        paperUploadParams.getPaperDraft();
        paperUploadParams.setPaperDraft(null);
    }

}