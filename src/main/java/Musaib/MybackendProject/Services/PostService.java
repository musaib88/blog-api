package Musaib.MybackendProject.Services;

import Musaib.MybackendProject.Payloads.PostDto;
import Musaib.MybackendProject.Payloads.UserDto;
import Musaib.MybackendProject.Utilities.PageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto , Integer userId, Integer categoryId );
    public  PostDto updatePost(PostDto postDto,Integer postId);
    public PageResponse getAllPost(Integer pageNum, Integer pageSize,String sortBy,String sortType);
    public  PostDto findById(Integer postId);
    public  void deletePost(Integer postId);
    public  List<PostDto> findByUserName(String  userDtoName);
            List<PostDto> findByUserId(Integer userId);
            List<PostDto> findByCategoryTitle(String categoryTitle);
            List<PostDto> findByCategoryId(Integer categoryId);


}
