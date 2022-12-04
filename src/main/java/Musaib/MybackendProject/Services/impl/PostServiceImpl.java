package Musaib.MybackendProject.Services.impl;

import Musaib.MybackendProject.Exceptions.ResourceNotFoundException;
import Musaib.MybackendProject.Models.Category;
import Musaib.MybackendProject.Models.ImageData;
import Musaib.MybackendProject.Models.Post;
import Musaib.MybackendProject.Models.User;
import Musaib.MybackendProject.Payloads.PostDto;
import Musaib.MybackendProject.Payloads.UserDto;
import Musaib.MybackendProject.Repositories.CategoryRep;
import Musaib.MybackendProject.Repositories.ImageDataRep;
import Musaib.MybackendProject.Repositories.PostRep;
import Musaib.MybackendProject.Repositories.UserRep;
import Musaib.MybackendProject.Services.PostService;
import Musaib.MybackendProject.Utilities.PageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRep postRep;
    @Autowired
    UserRep userRep;
    @Autowired
    CategoryRep categoryRep;
    @Autowired
    ModelMapper mapper;
    @Autowired
    ImageDataRep imageDataRep;

    @Override
    public PostDto createPost(PostDto postDto ,Integer userId, Integer categoryId) {
        User us = this.userRep.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        Category cs = this.categoryRep.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
        Post post = this.mapper.map(postDto, Post.class);
        post.setPostDate(new Date());
        post.setUser(us);
        post.setCategory(cs);
        this.postRep.save(post);
        PostDto postDto1 = this.mapper.map(post, PostDto.class);
        return postDto1;
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRep.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        post.setCategory(mapper.map(postDto.getCategory(), Category.class));
        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        post.setPostDate(new Date());

        this.postRep.save(post);
        PostDto postDto1 = this.mapper.map(post, PostDto.class);

        return postDto1;
    }

    @Override
    public PageResponse getAllPost(Integer pageNum, Integer pageSize,String sortBy,String sortType) {
        Sort sort=null;
        if(sortType.equals("desc"))
            sort=Sort.by(sortBy).descending();

        else  sort=Sort.by(sortBy).ascending();

        Pageable pageable= PageRequest.of(pageNum,pageSize,sort);
        Page<Post>s=this.postRep.findAll(pageable);
        List<Post> postlist=s.getContent();
        List<PostDto> list =postlist.stream().map(item ->this.mapper.map(item,PostDto.class)).collect(Collectors.toList());
        PageResponse pageResponse=new PageResponse();
        pageResponse.setContent(list);
        pageResponse.setPageNum(pageNum);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotalPages(s.getTotalPages());
        pageResponse.setLastPage(s.isLast());
        return pageResponse;
    }

    @Override
    public PostDto findById(Integer postId) {
        Post post=this.postRep.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));

        return this.mapper.map(post,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRep.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
        this.postRep.delete(post);


    }



    @Override
    public List<PostDto> findByUserName(String userName) {

        List<User> users = this.userRep.findByName(userName);
        List<List<Post>> postlist = new ArrayList<>();
        users.forEach(user -> postlist.add(postRep.findByUser(user)));
        List<PostDto> dtos=postlist.get(0).stream().map(item->this.mapper.map(item,PostDto.class)).collect(Collectors.toList());
       return   dtos;
    }

    @Override
    public List<PostDto> findByUserId(Integer userId) {
        User user=this.userRep.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userId",userId));
        List<Post> posts=this.postRep.findByUser(user);
        List<PostDto> dtos=posts.stream().map(item->this.mapper.map(item,PostDto.class)).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public List<PostDto> findByCategoryTitle(String categoryTitle) {
        Category category=this.categoryRep.findByCategoryTitle(categoryTitle);
        List<Post>posts=this.postRep.findByCategory(category);
        List<PostDto> dto=posts.stream().map(item->this.mapper.map(item,PostDto.class)).collect(Collectors.toList());
        return dto;

    }

    @Override
    public List<PostDto> findByCategoryId(Integer categoryId) {
        Category category=this.categoryRep.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId",categoryId));
        List<Post> posts=this.postRep.findByCategory(category);
        List<PostDto> dt=posts.stream().map(item->this.mapper.map(item,PostDto.class)).collect(Collectors.toList());

        return dt;
    }
}
