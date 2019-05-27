package vivo.chainpaper.response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.TestUtil;

import javax.transaction.Transactional;

import java.util.AbstractList;
import java.util.ArrayList;

import static org.junit.Assert.*;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class InfoPersonResponseTest {
    @Test
    public void name() {
        InfoPersonResponse res=new InfoPersonResponse("", "", "", 1, new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        TestUtil.getProperty(res);
        TestUtil.setProperty(res,"1");
        res.getPapers();
        res.setPapers(null);
    }
}