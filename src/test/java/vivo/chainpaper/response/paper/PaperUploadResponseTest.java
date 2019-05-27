package vivo.chainpaper.response.paper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.parameters.paper.PaperUploadParams;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class PaperUploadResponseTest {
    @Test
    public void name() {
        PaperUploadResponse paperUploadParams=new PaperUploadResponse("");
        paperUploadParams.getPaperId();
        paperUploadParams.setPaperId("1");
    }
}