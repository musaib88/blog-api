package Musaib.MybackendProject.Controllers;

import Musaib.MybackendProject.Models.ImageData;
import Musaib.MybackendProject.Payloads.ImageDataDto;
import Musaib.MybackendProject.Services.impl.ImageDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/image")
public class ImageDataController {
    @Autowired
    ImageDataServiceImpl data;
    @PostMapping("/imageFile/{postId}")
    public ResponseEntity<?>uploadImage(@RequestParam("imageFile") MultipartFile imageFile,@PathVariable("postId") Integer postId) throws IOException {

        ImageDataDto imageData = this.data.uploadFile(imageFile,postId);
        return new ResponseEntity<>(imageData,HttpStatus.ACCEPTED);
    }
    @GetMapping("/imageFile/{downloadImage}")
    public  ResponseEntity<?>downloadImage(@PathVariable("downloadImage") String imageName ){
        byte[] imageData=this.data.downloadFile(imageName);
        return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }
}
