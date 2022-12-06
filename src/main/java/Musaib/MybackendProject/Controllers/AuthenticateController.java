package Musaib.MybackendProject.Controllers;

import Musaib.MybackendProject.Models.User;
import Musaib.MybackendProject.Services.MyUserDetailService;
import Musaib.MybackendProject.Utilities.AuthenticationRequest;
import Musaib.MybackendProject.Utilities.AuthenticationResponse;
import Musaib.MybackendProject.Utilities.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Authenticate")
public class AuthenticateController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtHelper helper;
    @Autowired
    MyUserDetailService myUserDetailService;
    @PostMapping("/userDetails")
    public ResponseEntity<?> AuthenticateUser(@RequestBody AuthenticationRequest authenticationRequest) throws  Exception {
        try {authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword()));}
        catch (BadCredentialsException e){
            throw new Exception("username or password not found",e);
        }
        final UserDetails userDetails= myUserDetailService.loadUserByUsername(authenticationRequest.getUserName());

        final  String Jwt=this.helper.generateToken(userDetails);
        System.out.println("hello");

        return   new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(Jwt),HttpStatus.ACCEPTED);

    }

}
