package vivo.chainpaper.response.paper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;

import javax.transaction.Transactional;


@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class ListPaperGetResponseTest {
    ListPaperGetResponse res=new ListPaperGetResponse();

    @Test
    public void name() {
        ListPaperGetResponse res=new ListPaperGetResponse();
        ListPaperGetResponse res1=new ListPaperGetResponse();
        res.getPapers();
        res1.setPapers(null);
    }
}