package Musaib.MybackendProject.Controllers;

import Musaib.MybackendProject.Payloads.CommentDto;
import Musaib.MybackendProject.Services.impl.CommentServiceImpl;
import Musaib.MybackendProject.Services.impl.ImageDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto>uplodComment(@RequestBody CommentDto commentDto, @PathVariable("postId") Integer postId){
           CommentDto commentDto1=this.commentService.uploadComment(commentDto,postId);
         return ResponseEntity.ok(commentDto1);
    }
    @DeleteMapping("/{commentId}")
    public  ResponseEntity<String>deleteComment(@PathVariable("commentId") Integer commentId){
        this.commentService.delete(commentId);
        String message="uploaded successfully";

       return new  ResponseEntity<String>(message, HttpStatus.ACCEPTED);
    }
}
