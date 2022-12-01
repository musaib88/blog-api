package Musaib.MybackendProject.Services.impl;

import Musaib.MybackendProject.Exceptions.ResourceNotFoundException;
import Musaib.MybackendProject.Models.User;
import Musaib.MybackendProject.Payloads.UserDto;
import Musaib.MybackendProject.Repositories.UserRep;
import Musaib.MybackendProject.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private UserRep repo;

    @Override
    public UserDto createUser(UserDto user) {
        User us = this.toUser(user);
        return this.toDto(repo.save(us));
    }

    @Override
    public UserDto updateUser(UserDto user, Integer userId) {
        User us = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        us.setName(user.getName());
        us.setEmail(user.getEmail());
        us.setPassword(user.getPassword());
        us.setAbout(user.getAbout());


        return this.toDto(this.repo.save(us));
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User us = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        return this.toDto(us);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> user = repo.findAll();
        List<UserDto> us = user.stream().map(n -> this.toDto(n)).collect(Collectors.toList());
        return us;
    }

    @Override
    public void deleteUser(Integer userID) {
        User us = this.repo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("user", "id", userID));
        this.repo.delete(us);

    }

    private User toUser(UserDto userdt) {
        User user = new User();
        modelMapper.map(userdt, user);
        return user;

    }

    private UserDto toDto(User us) {
        UserDto ut = new UserDto();
        modelMapper.map(us, ut);
        return ut;
    }
}
