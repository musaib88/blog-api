package Musaib.MybackendProject.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private  Integer categoryId;
    @NotEmpty
    @Size(min = 4,max = 12,message = "title must be less than 13 chars")

    private  String categoryTitle;
    @NotEmpty
    @Size(min = 10,max = 100,message = "title must be less than 101")
    private  String categoryDescription;

    @Override
    public String toString() {
        return "CategoryDto{" +
                "categoryId=" + categoryId +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                '}';
    }
}
