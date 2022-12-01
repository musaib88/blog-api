package Musaib.MybackendProject.Models;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@AllArgsConstructor
@Setter
@Getter
@Entity
@Data
@Builder
@NoArgsConstructor
@Table(name ="Image_content")
public class ImageData {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
   private Integer imageId;

    @Lob
    @Column(name ="image_Data",length =1000)
    private byte[] imageData;
    private String imageName;
   private  String type;
   @ManyToOne
   @JoinColumn(name ="post_Image")
    private Post post;
}
