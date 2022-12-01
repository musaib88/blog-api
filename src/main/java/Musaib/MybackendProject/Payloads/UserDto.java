package Musaib.MybackendProject.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private  int id;
    @NotEmpty(message ="the username cant be Empty")
    @Size(min = 6,max = 10,message = "must be min 6 and max 10")
    private String name;

    @Email
    private String email;

    @NotEmpty(message ="the username cant be Empty")


    @Size(min = 4,max = 10,message = "should be min four and max 10 chars")
    @Pattern(regexp = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")
    
    private String password;

    @Size(min = 10,max=50,message = "should be min 10 and max 50 chars")
    @NotEmpty
    private String about;
}
