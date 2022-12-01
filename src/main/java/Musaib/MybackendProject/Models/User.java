package Musaib.MybackendProject.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "User_name", nullable = false, length = 100,unique = true)

    private String name;
    private String email;
    private String password;
    private String about;
    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
    private List<Post> posts= new ArrayList<>();
}
