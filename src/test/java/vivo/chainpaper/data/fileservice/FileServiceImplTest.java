//package trapx00.tagx00.data.fileservice;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import ImageResult;
//import TagDescriptionTuple;
//import ImageJob;
//import ImageWholeJob;
//import ImageMissionType;
//
//import java.util.ArrayList;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class FileServiceImplTest {
//
//    @Autowired
//    private FileService<ImageResult> fileService;
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void saveTuple() {
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("123");
//        TagDescriptionTuple tagDescriptionTuple = new TagDescriptionTuple(null, arrayList);
//        tagDescriptionTuple.setDescriptions(arrayList);
//        ImageWholeJob imageJob = new ImageWholeJob(ImageMissionType.WHOLE, tagDescriptionTuple);
//        ImageJob trial = imageJob;
//        ImageResult integer = new ImageResult(trial, null);
//        fileService.saveTuple(integer);
//    }
//
//    @Test
//    public void findImageResultById() {
//        ImageResult integer = fileService.findOne("1", ImageResult.class);

//        System.out.println(((ImageWholeJob) integer.getTextJob()).getTuple().getDescriptions().get(0));

//        System.out.println(((ImageWholeJob) integer.getImageJob()).getTuple().getDescriptions().get(0));

//    }
//
//    @Test
//    public void delete() {
//        fileService.delete("1", ImageResult.class);
//    }
//}