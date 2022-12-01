package Musaib.MybackendProject.Controllers;

import Musaib.MybackendProject.Payloads.PostDto;
import Musaib.MybackendProject.Payloads.UserDto;
import Musaib.MybackendProject.Services.impl.PostServiceImpl;
import Musaib.MybackendProject.Utilities.ApiResponse;
import Musaib.MybackendProject.Utilities.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<ApiResponse> createPost(@Valid @RequestBody PostDto postDto  ,@PathVariable("userId") Integer userId,@PathVariable("categoryId") Integer categoryId) {

        PostDto postDto1 = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Post created successfully ",true),HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public  ResponseEntity<ApiResponse>updatePost(@Valid @RequestBody PostDto postDto,@PathVariable("postId") Integer postId){
                PostDto us=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("post Updated succesfully",true),HttpStatus.OK);

    }

    @GetMapping("/")
    public  ResponseEntity<PageResponse>getAllPosts(@RequestParam(value = "pageNum",defaultValue ="0",required = false) Integer pageNum,@RequestParam (value = "pageSize",required = false,defaultValue ="2") Integer pageSize,
                       @RequestParam(value = "sortBy",defaultValue ="user",required = false) String sortBy,@RequestParam (value = "sortType",defaultValue ="asc",required = false)String sortType){
          PageResponse posts=this.postService.getAllPost(pageNum,pageSize,sortBy,sortType);
          return  ResponseEntity.ok(posts);
    }
    @GetMapping("/{postId}")
    public  ResponseEntity<PostDto>getById(@PathVariable ("postId") Integer postId){
         PostDto postDto=this.postService.findById(postId);
         return  ResponseEntity.ok(postDto);
    }
    @DeleteMapping("/{postId}")
    public  ResponseEntity<ApiResponse> deleteById(@PathVariable ("postId") Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("deleted successfully  ",true),HttpStatus.OK);
    }
    @GetMapping("/userName/{username}")
    public  ResponseEntity<List<PostDto>>getPostsByuser(@PathVariable ("username") String userName ){
        List<PostDto>  ps=this.postService.findByUserName(userName);
        return  new ResponseEntity<>(ps,HttpStatus.FOUND);

    }
    @GetMapping("/userId/{userId}")
    public  ResponseEntity<List<PostDto>>getPostsByUserId(@PathVariable ("userId") Integer userID){
        List<PostDto> posts= this.postService.findByUserId(userID);
        return  new ResponseEntity<>(posts,HttpStatus.FOUND);
    }
    @GetMapping("/categoryTitle/{categoryTitle}")
    public  ResponseEntity<List<PostDto>>getpostsByCategoryTitle(@PathVariable("categoryTitle") String categoryTitle){
        List<PostDto> posts=this.postService.findByCategoryTitle(categoryTitle);
        return  new ResponseEntity<>(posts,HttpStatus.FOUND);
    }
    @GetMapping("/categoryId/{categoryId}")
    public  ResponseEntity<List<PostDto>>getPostsByCategoryId(@PathVariable("categoryId") Integer categoryId){
    List<PostDto> posts=this.postService.findByCategoryId(categoryId);
    return  new ResponseEntity<>(posts,HttpStatus.FOUND);
    }
}
