package vivo.chainpaper.springcontroller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.dao.CollabrationDao;
import vivo.chainpaper.entity.Collabration;
import vivo.chainpaper.entity.Paper;
import vivo.chainpaper.parameters.Collabrate.CollabrateResultParam;
import vivo.chainpaper.util.MockNameutil;
import vivo.chainpaper.util.UserInfoUtil;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class CollabrateControllerTest {
    @Before
    public void setup() {
        UserInfoUtil.nameUtil = new MockNameutil();
    }

    @Autowired
    CollabrateController collabrateController;

    @Autowired
    CollabrationDao collabrationDao;

    @Test
    public void collabrateTest(){
        List<Collabration>  collabrations = collabrationDao.findAll();
        if(collabrations.size() > 0){
            String ID = collabrations.get(0).getCollabrationID();
            collabrateController.invitation(new CollabrateResultParam(collabrations.get(0).getPaperId()));
            collabrateController.request(new CollabrateResultParam(collabrations.get(0).getPaperId()));
            collabrateController.invitationList(collabrations.get(0).getCollabrationID());
            collabrateController.acceptList(collabrations.get(0).getCollabrationID());
            collabrateController.acceptInvitation(collabrations.get(0).getCollabrationID());
            collabrateController.accept(collabrations.get(0).getCollabrationID());

        }
    }



}