package Musaib.MybackendProject.Controllers;

import Musaib.MybackendProject.Payloads.UserDto;
import Musaib.MybackendProject.Services.impl.UserServiceImpl;
import Musaib.MybackendProject.Utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl service;

    //post create user
    @PostMapping("/")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserDto userDto){
        UserDto us=this.service.createUser(userDto);
     return new   ResponseEntity<ApiResponse>(new ApiResponse("logged in  succesfully",true),HttpStatus.ACCEPTED);
    }
    @PutMapping("/{userId}")
    public  ResponseEntity<ApiResponse>updateUser(@Valid @RequestBody UserDto userDto ,@PathVariable("userId")int id){
        UserDto user=this.service.updateUser(userDto,id);

      return new  ResponseEntity<ApiResponse>(new ApiResponse("updated Successfully",true),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable("userId") int id){
        this.service.deleteUser(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("deleted successfully",true),HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>>getall(){
           List<UserDto > userDtos=this.service.getAllUsers();
           return  ResponseEntity.ok(userDtos);



    }
    @GetMapping("/{userId}")
    public  ResponseEntity<UserDto>getById(@PathVariable("userId") int id){
       UserDto user= this.service.getUserById(id);
       return  ResponseEntity.ok(user);
    }

}
