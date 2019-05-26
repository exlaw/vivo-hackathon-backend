package vivo.chainpaper.bl.mission;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.blservice.mission.WorkerMissionBlService;
import vivo.chainpaper.exception.viewexception.SystemException;
import vivo.chainpaper.publicdatas.mission.MissionType;
import vivo.chainpaper.util.FileUtils;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class  WorkerMissionBlServiceImplTest {
    @Autowired
    private WorkerMissionBlService workerMissionBlService;

    @Test
    public void queryOnesAllMissions() {

//        try {
//            assertEquals("凌尊", workerMissionBlService.queryOnesAllMissions("凌尊",new PagingQueryVo()).getInstances().get(0).getWorkerUsername());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void abort() {

        try {
            assertEquals(null, workerMissionBlService.abort("image_1","凌尊"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getInstanceInformation() {
        try {
            Assert.assertEquals(MissionType.IMAGE, workerMissionBlService.getInstanceInformation("image_1","凌尊").getDetail().getMissionType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void identifyImage() {
        try {
            System.out.println(workerMissionBlService.identifyImage(new MockMultipartFile("test", FileUtils.toByteArray("/Users/apple/Downloads/IMG_3853.JPG"))));
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}