package Musaib.MybackendProject.Services;

import Musaib.MybackendProject.Payloads.CommentDto;

public interface CommentService {
     public CommentDto uploadComment(CommentDto commentDto,Integer postId);
     public  void delete(Integer commentId);
}
