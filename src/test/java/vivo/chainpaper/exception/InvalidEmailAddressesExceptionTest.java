package vivo.chainpaper.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;

import javax.transaction.Transactional;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class InvalidEmailAddressesExceptionTest {
    @Test
    public void test(){
        InvalidEmailAddressesException invalidEmailAddressesException=new InvalidEmailAddressesException();
        invalidEmailAddressesException.getResponse();
    }

}