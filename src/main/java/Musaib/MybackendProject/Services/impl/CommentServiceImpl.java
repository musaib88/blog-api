package Musaib.MybackendProject.Services.impl;

import Musaib.MybackendProject.Exceptions.ResourceNotFoundException;
import Musaib.MybackendProject.Models.Comment;
import Musaib.MybackendProject.Models.Post;
import Musaib.MybackendProject.Models.User;
import Musaib.MybackendProject.Payloads.CommentDto;
import Musaib.MybackendProject.Repositories.CommentRep;
import Musaib.MybackendProject.Repositories.PostRep;
import Musaib.MybackendProject.Repositories.UserRep;
import Musaib.MybackendProject.Services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRep commentRep;
    @Autowired
    UserRep userRep;

    @Autowired
    PostRep postRep;
    @Autowired
    ModelMapper mapper;
    @Override
    public CommentDto uploadComment(CommentDto commentDto, Integer postId,Integer userId) {
        Post post = this.postRep.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postid",postId));
        Comment comment=this.mapper.map(commentDto,Comment.class);
        User us =this.userRep.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userid",userId));
        comment.setPost(post);

        comment.setUser(us);
        this.commentRep.save(comment);

        return this.mapper.map(comment,CommentDto.class);
    }

    @Override
    public void delete(Integer commentId) {
        Comment comment=this.commentRep.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId:",0));
        this.commentRep.delete(comment);

    }
}
