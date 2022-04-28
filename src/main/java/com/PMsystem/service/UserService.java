package com.PMsystem.service;

import com.PMsystem.entity.ProjectEntity;
import com.PMsystem.entity.Role;
import com.PMsystem.entity.UserEntity;
import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.exception.NotFoundException;
import com.PMsystem.model.User;
import com.PMsystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void addUser(UserEntity user) throws AlreadyExistsException {
        UserEntity userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null){
            throw new AlreadyExistsException("User with this username already exists! Try something else!");
        }
        user.setRole(Role.USER);
        user.setAdmin(false);
        userRepo.save(user);
    }

    public List<User> loadUsers() {
        return userRepo.findAll().stream().map(User::toModel).collect(Collectors.toList());
    }

    public User loadUserById(Long id) throws NotFoundException {
        Optional<UserEntity> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("Can't find user!");
        }
        return User.toModel(user.get());
    }

    public void changeRole(Long id) throws NotFoundException {
        Optional<UserEntity> userFromDb = userRepo.findById(id);
        if (userFromDb.isEmpty()) {
            throw new NotFoundException("Can't find user!");
        }
        UserEntity user = userFromDb.get();
        user.setAdmin(!user.getAdmin());
        userRepo.save(user);
    }

    public void deleteUser(Long id) throws NotFoundException {
        Optional<UserEntity> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("Can't find user!");
        }
        userRepo.delete(user.get());
    }

    public ProjectEntity addProjectToUser(Long userId, ProjectEntity project) throws NotFoundException, AlreadyExistsException {
        Optional<UserEntity> userFromDb = userRepo.findById(userId);
        if (userFromDb.isEmpty()){
            throw new NotFoundException("User doesn't exists!");
        }
        UserEntity user = userFromDb.get();
        Optional<ProjectEntity> sameProjectName = user.getProjects().stream()
                .filter(pr -> pr.equals(project))
                .findAny();
        if (sameProjectName.isPresent()){
            throw new AlreadyExistsException("Project was already created!");
        }
        project.setUser(user);
        return project;
    }
}
