package Musaib.MybackendProject.Payloads;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;

@NoArgsConstructor
@Setter
@Getter
@Data
public class ImageDataDto {
    private String imageName;
    @Lob
    private byte[] imageData;
    private  String type;
}
