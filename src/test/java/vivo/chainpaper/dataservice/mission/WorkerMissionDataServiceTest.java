package vivo.chainpaper.dataservice.mission;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.exception.viewexception.MissionAlreadyAcceptedException;
import vivo.chainpaper.exception.viewexception.SystemException;
import vivo.chainpaper.publicdatas.instance.MissionInstanceState;
import vivo.chainpaper.publicdatas.mission.MissionType;
import vivo.chainpaper.vo.mission.instance.InstanceDetailVo;
import vivo.chainpaper.vo.mission.instance.InstanceVo;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerMissionDataServiceTest {
    @Autowired
    private WorkerMissionDataService workerMissionDataService;
    private InstanceDetailVo instance ;

    @Before
    public void setUp() throws Exception {
         instance=new InstanceDetailVo(MissionType.IMAGE,new InstanceVo("0",1,1,100,"123",
                "秦牧", MissionInstanceState.IN_PROGRESS,"1",new Date(),new Date(),false,0));
        workerMissionDataService.saveInstanceDetailVo(instance);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMissionByMissionIdAndMissionType(){

    }
    @Test
    public void saveInstance() throws SystemException {
        String result ;
        try {
            result = workerMissionDataService.saveInstanceDetailVo(instance);
        } catch (MissionAlreadyAcceptedException |IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getInstanceByWorkerUsername() throws SystemException {

        Assert.assertEquals(1, workerMissionDataService.getInstanceByWorkerUsername("秦牧")[0].getMissionId());
    }

    @Test
    public void getInstanceByUsernameAndMissionId() throws SystemException {
        Assert.assertEquals("秦牧", workerMissionDataService.getInstanceByUsernameAndMissionId("秦牧", "1",MissionType.IMAGE ).getWorkerUsername());
    }

    @Test
    public void deleteInstanceByMissionIdAndUsername(){
        try {
            workerMissionDataService.deleteInstanceByMissionIdAndUsername("1","秦牧",MissionType.IMAGE);
        }catch (ClassNotFoundException|SystemException|IOException e){

        }

    }
}