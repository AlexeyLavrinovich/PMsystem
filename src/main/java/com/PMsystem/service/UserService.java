package com.PMsystem.service;

import com.PMsystem.entity.Role;
import com.PMsystem.entity.UserEntity;
import com.PMsystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    public boolean addUser(UserEntity user){
        UserEntity userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null){
            return false;
        }
        user.setAdmin(false);
        user.setRoles(Role.USER);
        userRepo.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}
