package vivo.chainpaper.entity.account;

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
public class UserTest {
    User user=new User();
    User user2=new User("","","","","");

    @Test
    public void test(){
        TestUtil.getProperty(user);
        TestUtil.getProperty(user2);
        TestUtil.setProperty(user2,"1");
    }
}