package Musaib.MybackendProject.Payloads;

import Musaib.MybackendProject.Models.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;

@NoArgsConstructor
@Setter
@Getter
public class CommentDto {
    private  Integer commentId;
    private  String   commentContent;

}

