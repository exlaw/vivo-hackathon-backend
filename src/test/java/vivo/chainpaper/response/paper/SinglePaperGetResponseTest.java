package vivo.chainpaper.response.paper;

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
public class SinglePaperGetResponseTest {
    @Test
    public void name() {
        SinglePaperGetResponse r=new SinglePaperGetResponse();
        r.getPaper();
        SinglePaperGetResponse r1=new SinglePaperGetResponse(null);
        r1.setPaper(null);
    }
}