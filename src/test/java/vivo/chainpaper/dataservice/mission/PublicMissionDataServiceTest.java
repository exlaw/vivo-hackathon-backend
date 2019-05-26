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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PublicMissionDataServiceTest {
    @Autowired
    private PublicMissionDataService publicMissionDataService;

    private RequesterMissionDataService requesterMissionDataService;
    private ImageMission mission;
    private MissionProperties missionProperties;
    private MissionInstanceItemVo missionInstanceItem;
    private WorkerMissionDataService workerMissionDataService;


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
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMissions() {
        try{
            requesterMissionDataService.saveMission(mission);
            Assert.assertEquals("凌尊", publicMissionDataService.getMissions()[0].getRequesterUsername());
        }catch (SystemException e){

        }catch (IOException e){
            e.printStackTrace();
        }


    }
    @Test
    public void getOneMissionDetail() {
        try{
            Assert.assertEquals("凌尊", publicMissionDataService.getOneMissionDetail("1", MissionType.IMAGE).getPublicItem().getRequesterUsername());

        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (MissionIdDoesNotExistException e){
            e.getResponse();
        }

    }

    @Test
    public void  getInstances(){
        try {

            InstanceDetailVo instance=new InstanceDetailVo(MissionType.IMAGE,new InstanceVo("0",1,1,100,"123",
                    "123", MissionInstanceState.IN_PROGRESS,"1",new Date(),new Date(),false,0));
            workerMissionDataService.saveInstanceDetailVo(instance);
            assertEquals(1,publicMissionDataService.getInstances().length);
        }catch (SystemException e){
            e.printStackTrace();
        }catch (MissionAlreadyAcceptedException e){
            e.getResponse();
        }catch (IOException e){
            e.printStackTrace();
        }

    }


}