package com.PMsystem.service;

import com.PMsystem.entity.Role;
import com.PMsystem.entity.UserEntity;
import com.PMsystem.exception.UserAlreadyExistsException;
import com.PMsystem.exception.UserNotFoundException;
import com.PMsystem.model.User;
import com.PMsystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public boolean addUser(UserEntity user) throws UserAlreadyExistsException {
        UserEntity userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null){
            throw new UserAlreadyExistsException("User with this username already exists! Try something else!");
        }
        user.setRole(Role.USER);
        userRepo.save(user);
        return true;
    }

    public List<User> loadUsers() {
        return userRepo.findAll().stream().map(userEntity -> {
            return User.toModel(userEntity);
        }).collect(Collectors.toList());
    }

    public User loadUserById(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("Can't find user!");
        }
        return User.toModel(user);
    }

    public void changeRole(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("Can't find user!");
        }
        user.setAdmin(!user.getAdmin());
        userRepo.save(user);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("Can't find user!");
        }
        userRepo.delete(user);
    }
}
