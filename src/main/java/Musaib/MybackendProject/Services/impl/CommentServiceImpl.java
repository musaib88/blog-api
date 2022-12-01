package Musaib.MybackendProject.Services.impl;

import Musaib.MybackendProject.Exceptions.ResourceNotFoundException;
import Musaib.MybackendProject.Models.Comment;
import Musaib.MybackendProject.Models.Post;
import Musaib.MybackendProject.Payloads.CommentDto;
import Musaib.MybackendProject.Repositories.CommentRep;
import Musaib.MybackendProject.Repositories.PostRep;
import Musaib.MybackendProject.Services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRep commentRep;
    @Autowired
    PostRep postRep;
    @Autowired
    ModelMapper mapper;
    @Override
    public CommentDto uploadComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRep.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postid",postId));
        Comment comment=this.mapper.map(commentDto,Comment.class);
        comment.setPost(post);
        this.commentRep.save(comment);

        return this.mapper.map(comment,CommentDto.class);
    }

    @Override
    public void delete(Integer commentId) {
        Comment comment=this.commentRep.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId:",0));
        this.commentRep.delete(comment);

    }
}
