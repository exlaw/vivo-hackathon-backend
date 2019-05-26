package vivo.chainpaper.bl.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.blservice.account.UserBlService;
import vivo.chainpaper.dao.account.UserDao;
import vivo.chainpaper.entity.account.User;
import vivo.chainpaper.exception.CannotRegisterException;
import vivo.chainpaper.exception.UserAlreadyExistsException;
import vivo.chainpaper.exception.UsernameDoesNotFoundException;
import vivo.chainpaper.exception.WrongUsernameOrPasswordException;
import vivo.chainpaper.parameters.user.RegisterParams;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class UserBlServiceImplTest {
    @Autowired
    UserBlServiceImpl userBlService;
    @Autowired
    UserDao userDao;
    @Test
    public void login() throws WrongUsernameOrPasswordException, UsernameDoesNotFoundException, CannotRegisterException, UserAlreadyExistsException {
        try {
            userBlService.register(new RegisterParams("wrh1","123","22@edu.cn","teacher"));
        } catch (UserAlreadyExistsException e) {
            //user already exsits
            User user=userDao.findUserByUsername("wrh1");
            userDao.delete(user);

                userBlService.register(new RegisterParams("wrh1","123","22@edu.cn","teacher"));

        }finally {
                userBlService.login("wrh1","123");

        }
    }

    @Test
    public void login2() {
        //专门测试异常情况
        User user=userDao.findUserByUsername("wrh2");
        if(user!=null){
            userDao.delete(user);
        }
        try {
            userBlService.login("wrh2","11");
        } catch (WrongUsernameOrPasswordException e) {

        } catch (UsernameDoesNotFoundException e) {

        }
        try {
            userBlService.register(new RegisterParams("wrh2","123","22@edu.cn","teacher"));
            userBlService.login("wrh2","11");
        } catch (UserAlreadyExistsException e) {

        } catch (WrongUsernameOrPasswordException e) {

        } catch (UsernameDoesNotFoundException e) {

        }

        try {
            userBlService.register(new RegisterParams("wrh2","123","22@edu.cn","teacher"));
        } catch (UserAlreadyExistsException e) {
        }
        User user2=userDao.findUserByUsername("wrh2");
        userDao.delete(user2);
    }
}