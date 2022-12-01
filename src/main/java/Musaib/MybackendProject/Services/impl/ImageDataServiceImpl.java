package Musaib.MybackendProject.Services.impl;

import Musaib.MybackendProject.Exceptions.ResourceNotFoundException;
import Musaib.MybackendProject.Models.ImageData;
import Musaib.MybackendProject.Models.Post;
import Musaib.MybackendProject.Payloads.ImageDataDto;
import Musaib.MybackendProject.Repositories.ImageDataRep;
import Musaib.MybackendProject.Repositories.PostRep;
import Musaib.MybackendProject.Services.ImageDataService;
import Musaib.MybackendProject.Utilities.ImageDataUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
@Service

public class ImageDataServiceImpl implements ImageDataService {
    @Autowired
    ImageDataRep rep;
    @Autowired
    PostRep postRep;
    @Autowired
    ModelMapper mapper;

    @Override
    public ImageDataDto uploadFile(MultipartFile file, Integer postId) throws IOException {
          Post post=this.postRep.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
          ImageData imageData=new ImageData();
          imageData.setPost(post);

         this.rep.save(ImageData.builder().imageName(file.getOriginalFilename()).type(file.getContentType()).imageData(ImageDataUtility.compressImage(file.getBytes())).build());
//          if(imageData!=null){
//              return "uploaded successfully";
//          }
        return this.mapper.map(imageData,ImageDataDto.class);
    }

    @Override
    public byte[] downloadFile(String fileName) {
        Optional<ImageData> imageData=this.rep.findByImageName(fileName);
       byte[] images= ImageDataUtility.decompressImage(imageData.get().getImageData());
        return images;
    }

    @Override
    public ImageData findByImageId(Integer imageId) {
         ImageData imageData=this.rep.findById(imageId).orElseThrow(()->new ResourceNotFoundException("Image","imageData",imageId));
        return imageData;
    }

}
