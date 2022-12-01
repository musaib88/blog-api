package Musaib.MybackendProject.Repositories;

import Musaib.MybackendProject.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRep  extends JpaRepository<Comment,Integer> {
}
