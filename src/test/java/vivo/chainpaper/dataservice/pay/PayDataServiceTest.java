package vivo.chainpaper.dataservice.pay;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import vivo.chainpaper.dataservice.account.UserDataService;
import vivo.chainpaper.exception.viewexception.SystemException;
import vivo.chainpaper.vo.mission.pay.PayVo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PayDataServiceTest {
    @Autowired
    private PayDataService payDataService;
    private UserDataService userDataService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void updateUser(){
        try{
            payDataService.updateUser(new PayVo(100),"123");
            assertEquals(100,userDataService.getUserByUsername("123").getCredits());
        }
        catch (SystemException e){

        }

    }
}
