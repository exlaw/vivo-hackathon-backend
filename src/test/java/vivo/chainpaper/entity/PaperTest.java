package vivo.chainpaper.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.TestUtil;
import vivo.chainpaper.parameters.paper.Reference;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class PaperTest {
    Paper paper=new Paper();


    @Test
    public void test1(){
        Reference[] refs=new Reference[2];
        refs[0]=new Reference("published","","","");
        refs[1]=new Reference("chainpaper","","","");
        Paper paper2=new Paper("","","","",refs,"","","","","");
        TestUtil.getProperty(paper2);
        TestUtil.setProperty(paper2,"1");

    }
}