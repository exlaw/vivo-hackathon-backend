package vivo.chainpaper.response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;

import javax.transaction.Transactional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class RequestResponseTest {
    @Test
    public void name() {
        RequestResponse r=new RequestResponse("");
        r.getCollabrationRequestId();
        r.setCollabrationRequestId("1");
    }
}