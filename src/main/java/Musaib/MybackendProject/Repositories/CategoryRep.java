package Musaib.MybackendProject.Repositories;

import Musaib.MybackendProject.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Locale;

public interface CategoryRep extends JpaRepository<Category,Integer> {
    public  Category findByCategoryTitle(String categoryTitle);
}
