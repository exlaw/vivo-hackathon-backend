package vivo.chainpaper.blservice.upload;

import vivo.chainpaper.exception.SystemException;
import vivo.chainpaper.response.upload.UploadImageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadBlService {
    /**
     * Upload the image of the food
     *
     * @param multipartFile
     * @return the url of the image
     */
    UploadImageResponse uploadFiles(MultipartFile multipartFile) throws SystemException;

    UploadImageResponse uploadFilesWithUser(MultipartFile multipartFile, String username) throws SystemException;

    String uploadImage(String key, String suffix, byte[] bytes) throws SystemException;

    String uploadFile(String key, String suffix, String filePath) throws SystemException;
}
