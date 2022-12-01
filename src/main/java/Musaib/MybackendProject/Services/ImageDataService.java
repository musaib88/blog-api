package Musaib.MybackendProject.Services;

import Musaib.MybackendProject.Models.ImageData;
import Musaib.MybackendProject.Payloads.ImageDataDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageDataService {
    public ImageDataDto uploadFile(MultipartFile file , Integer postId) throws IOException;
    public  byte[] downloadFile(String fileName);
    public ImageData findByImageId(Integer imageId);
}
