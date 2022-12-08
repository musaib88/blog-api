package Musaib.MybackendProject.Repositories;

import Musaib.MybackendProject.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserRep extends JpaRepository<User,Integer> {
    User findByName(String name);
    User findByEmail(String email);


}
