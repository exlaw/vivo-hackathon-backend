package vivo.chainpaper.bl.upload;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import vivo.chainpaper.blservice.upload.ImageUploadBlService;
import vivo.chainpaper.dao.account.UserDao;
import vivo.chainpaper.entity.account.User;
import vivo.chainpaper.exception.SystemException;
import vivo.chainpaper.response.upload.UploadImageResponse;
import vivo.chainpaper.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Service
public class ImageUploadBlServiceImpl implements ImageUploadBlService {

    private final UserDao userDao;
    private final static String TEMP_PATH = PathUtil.getTmpPath();
    private static final long EXPIRATION = Long.MAX_VALUE;

    @Value("${oos.accessKey}")
    private String accessKey;
    @Value("${oos.secretKey}")
    private String secretKey;
    @Value("${oos.endPoint}")
    private String endPoint;
    @Value("${oos.bucketName}")
    private String bucketName;

    @Autowired
    public ImageUploadBlServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Upload the image of the mission
     *
     * @param multipartFile
     * @return the url of the image
     */
    @Override
    public UploadImageResponse uploadFiles(MultipartFile multipartFile) throws SystemException {
        try {
            String uid = generateImageKey();
            String url = uploadImage(uid, multipartFile.getOriginalFilename().split("\\.")[1], multipartFile.getBytes());
            return new UploadImageResponse(uid, url);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SystemException();
        }
    }

    @Override
    public UploadImageResponse uploadFilesWithUser(MultipartFile multipartFile, String username) throws SystemException {
        try {
            String uid = generateImageKey();
            User user = userDao.findUserByUsername(username);
            uid = "user" + uid;
            String url = uploadImage(uid, multipartFile.getOriginalFilename().split("\\.")[1], multipartFile.getBytes());
            return new UploadImageResponse(uid, url);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SystemException();
        }
    }

    private String generateImageKey() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public String uploadImage(String key, String suffix, byte[] bytes) throws SystemException {
        key = key + "\\." + suffix;
        try {
            //保存到临时文件
            File file = new File(TEMP_PATH + "/" + key);
            FileImageOutputStream fileWriter = new FileImageOutputStream(file);
            fileWriter.write(bytes);
            fileWriter.close();

            //上传图片
            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
            AmazonS3 oos = new AmazonS3Client(credentials);
            oos.setEndpoint(endPoint);
            oos.putObject(bucketName, key, file);

            //生成共享地址
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucketName, key);
            generatePresignedUrlRequest.setExpiration(new Date(EXPIRATION));
            URL url = oos.generatePresignedUrl(generatePresignedUrlRequest);
            return url.toURI().toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException();
        }
    }

    public String uploadFile(String key, String suffix, String filePath) throws SystemException {
        key = key + "\\." + suffix;
        try {
            //保存到临时文件
            File file = new File(filePath);

            //上传图片
            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
            AmazonS3 oos = new AmazonS3Client(credentials);
            oos.setEndpoint(endPoint);
            oos.putObject(bucketName, key, file);

            //生成共享地址
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucketName, key);
            generatePresignedUrlRequest.setExpiration(new Date(EXPIRATION));
            URL url = oos.generatePresignedUrl(generatePresignedUrlRequest);
            return url.toURI().toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException();
        }
    }
}
