package vivo.chainpaper.bl.paper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.entity.Paper;
import vivo.chainpaper.parameters.paper.PaperDraft;

import javax.transaction.Transactional;

import java.net.SocketTimeoutException;

import static org.junit.Assert.*;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class PaperBlServiceImplTest {
    @Autowired PaperBlServiceImpl paperBlService;

    @Test
    public void test1(){
        try {
            paperBlService.addPaperToChainStore(new PaperDraft(null, "1", "1", "1", "1", "1", "1", "1"), "2");
        }catch (Exception e){

        }
        Paper paper=new Paper();
        paper.setId("7");
        paperBlService.addPaperToDatabase(paper);
    }

}