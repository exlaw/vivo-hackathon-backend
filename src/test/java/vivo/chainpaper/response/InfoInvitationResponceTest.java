package vivo.chainpaper.response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.TestUtil;

import javax.transaction.Transactional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class InfoInvitationResponceTest {
    InfoInvitationResponce responce=new InfoInvitationResponce("","","","","");

    @Test
    public void name() {
        TestUtil.getProperty(responce);
        TestUtil.setProperty(responce,"");
    }

}