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
public class PaperDraftTest {
    Reference[] refs=new Reference[1];
    PaperDraft paperDraft=new PaperDraft(refs,"1","2","3","4","5","6","7");

    @Test
    public void test(){
        TestUtil.getProperty(paperDraft);
        TestUtil.setProperty(paperDraft,"1");
        paperDraft.setReference(null);
        paperDraft.setRefs(null);
        paperDraft.getReference();
    }
}