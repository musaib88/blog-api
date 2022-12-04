package Musaib.MybackendProject.Repositories;

import Musaib.MybackendProject.Models.Category;
import Musaib.MybackendProject.Models.Post;
import Musaib.MybackendProject.Models.User;
import Musaib.MybackendProject.Payloads.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PostRep extends JpaRepository<Post, Integer> {
     List<Post> findByUser(User user);
     List<Post> findByCategory(Category category);


}
