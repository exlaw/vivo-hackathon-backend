package vivo.chainpaper.dataservice.mission;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.entity.mission.ImageMission;
import vivo.chainpaper.entity.mission.Mission;
import vivo.chainpaper.exception.viewexception.MissionAlreadyAcceptedException;
import vivo.chainpaper.exception.viewexception.MissionIdDoesNotExistException;
import vivo.chainpaper.exception.viewexception.SystemException;
import vivo.chainpaper.publicdatas.instance.MissionInstanceState;
import vivo.chainpaper.publicdatas.mission.MissionType;
import vivo.chainpaper.vo.mission.image.ImageMissionType;
import vivo.chainpaper.vo.mission.instance.InstanceDetailVo;
import vivo.chainpaper.vo.mission.instance.InstanceVo;
import vivo.chainpaper.vo.mission.instance.MissionInstanceItemVo;
import vivo.chainpaper.vo.mission.missiontype.MissionProperties;
import vivo.chainpaper.vo.mission.requester.MissionFinalizeVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RequesterMissionDataServiceTest {
    @Autowired
    private RequesterMissionDataService requesterMissionDataService;
    private WorkerMissionDataService workerMissionDataService;



    private InstanceDetailVo instance ;

    private MissionProperties missionProperties;
    private MissionInstanceItemVo missionInstanceItem;
    private Mission mission;

    @Before
    public void setUp() throws Exception {
        missionProperties = new MissionProperties(MissionType.IMAGE);
        ArrayList<String> topics = new ArrayList<>();
        topics.add("风景画");
        topics.add("灾难画");
        ArrayList<String> allowedTags = new ArrayList<>();
        allowedTags.add("风景画");
        allowedTags.add("灾难画");
        ArrayList<ImageMissionType> imageMissionTypes = new ArrayList<>();
        imageMissionTypes.add(ImageMissionType.PART);
        imageMissionTypes.add(ImageMissionType.DISTRICT);
        ArrayList<String> urls = new ArrayList<>();
        urls.add("https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/0E/00/ChMkJlnJ4TOIAyeVAJqtjV-XTiAAAgzDAE7v40Amq2l708.jpg");
        urls.add("http://pic1.16xx8.com/allimg/170801/1-1FP116442T62.jpg");
        urls.add("http://pic1.16xx8.com/allimg/170801/1-1FP116442T62.jpg");
        mission = new ImageMission();
        missionInstanceItem = new MissionInstanceItemVo(0, "张三", MissionInstanceState.SUBMITTED, new Date(), new Date(), 100, 100);
        instance=new InstanceDetailVo(MissionType.IMAGE,new InstanceVo("0",1,1,100,"123",
                "秦牧",MissionInstanceState.IN_PROGRESS,"1",new Date(),new Date(),false,0));


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveMission() {
        try {
            requesterMissionDataService.saveMission(mission);
        } catch (SystemException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getMissionByMissionIdAndMissionType() {
        try {
            requesterMissionDataService.saveMission(mission);
            Assert.assertEquals("123", requesterMissionDataService.getMissionByMissionId("1").getTitle());
        } catch (SystemException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (MissionIdDoesNotExistException e){
            e.getResponse();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    @Test
    public void getInstanceByInstanceId() {
        try {
            requesterMissionDataService.saveMission(mission);
            workerMissionDataService.saveInstanceDetailVo(instance);
        } catch (SystemException e) {
            e.printStackTrace();
        }catch (MissionAlreadyAcceptedException e){

        }catch (IOException e){
            e.printStackTrace();
        }
        Assert.assertEquals(MissionType.IMAGE, requesterMissionDataService.getInstanceByInstanceId("1", MissionType.IMAGE).getMissionType());
    }

    @Test
    public void getInstancesByMissionId() {
        try {
            requesterMissionDataService.saveMission(mission);
            workerMissionDataService.saveInstanceDetailVo(instance);
        } catch (SystemException e) {
            e.printStackTrace();
        }catch (MissionAlreadyAcceptedException e){

        }catch (IOException e){

        }
        Assert.assertEquals("秦牧", requesterMissionDataService.getInstancesByMissionId("1", MissionType.IMAGE)[0].getWorkerUsername());
    }

    @Test
    public void updateMission() {
        try {
            requesterMissionDataService.saveMission(mission);
            requesterMissionDataService.updateMission("1",100,MissionType.IMAGE);
            Assert.assertEquals(110, requesterMissionDataService.getMissionByMissionId("1").getCredits());
        } catch (SystemException |IOException|MissionIdDoesNotExistException|ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateInstance(){
        try{
            workerMissionDataService.saveInstanceDetailVo(instance);
            requesterMissionDataService.updateInstance("1",new MissionFinalizeVo(1,100,"good"),MissionType.IMAGE);
            Assert.assertEquals("good",requesterMissionDataService.getInstanceByInstanceId("1",MissionType.IMAGE).getInstance().getComment());
        }catch (SystemException e){

        }catch (MissionAlreadyAcceptedException |IOException e){

        }
    }
}