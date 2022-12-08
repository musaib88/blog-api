package Musaib.MybackendProject.Services;

import Musaib.MybackendProject.Models.User;
import Musaib.MybackendProject.Repositories.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRep userRep;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user=this.userRep.findByEmail(username);
        return user;
    }
}
