package Musaib.MybackendProject.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name ="Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @NotEmpty
    @Size(min = 4, max = 30, message = "TITLE SHOULD BE ATLEAST OF EIGHT CHARS N MAX 15")
    private String postTitle;
    @NotEmpty
    @Size(min = 5, max = 1000, message = "content SHOULD BE ATLEAST OF 150 CHARS N MAX 1000")
    private String postContent;

    private Date postDate;
    @ManyToOne
    @JoinColumn(name ="category_Id", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name ="user_Id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post")
    private Set<ImageData> images=new HashSet<>();
    @OneToMany(mappedBy ="post" )
    private  Set<Comment> comments=new HashSet<>();

}
