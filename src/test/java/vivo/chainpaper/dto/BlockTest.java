package vivo.chainpaper.dto;

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
public class BlockTest {

    @Test
    public void test(){
        Block b=new Block(0,0);
        TestUtil.getProperty(b);
        TestUtil.setProperty(b,"1");
    }
}