package Musaib.MybackendProject.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer categoryId;
    private  String categoryTitle;
    private  String categoryDescription;
    @OneToMany(mappedBy = "category",cascade =CascadeType.ALL )
    private  List<Post> posts=new ArrayList<>();


}
