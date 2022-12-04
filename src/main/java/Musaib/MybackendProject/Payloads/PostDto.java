package Musaib.MybackendProject.Payloads;

import Musaib.MybackendProject.Models.Category;
import Musaib.MybackendProject.Models.Comment;
import Musaib.MybackendProject.Models.ImageData;
import Musaib.MybackendProject.Models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
@Setter
@Getter

public class PostDto {

    private  String postTitle;
    private  String postContent;
    private Date postDate;
    private CategoryDto category;
    private UserDto user;
    private  Set<ImageDataDto> images=new HashSet<>();
    private  Set<CommentDto> comments=new HashSet<>();

}
