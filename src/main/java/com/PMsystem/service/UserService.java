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

    public UserEntity findUserById(Long id) throws NotFoundException {
        Optional<UserEntity> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("Can't find user!");
        }
        return user.get();
    }

    public User loadUserById(Long id) throws NotFoundException {
        return User.toModel(findUserById(id));
    }

    public void changeRole(Long id) throws NotFoundException {
        UserEntity user = findUserById(id);
        user.setAdmin(!user.getAdmin());
        userRepo.save(user);
    }

    public void deleteUser(Long id) throws NotFoundException {
        userRepo.delete(findUserById(id));
    }

    public Optional<ProjectEntity> findProjectByName(UserEntity user, ProjectEntity project) {
        Optional<ProjectEntity> sameProjectName = user.getProjects().stream()
                .filter(pr -> pr.equals(project))
                .findAny();
        return sameProjectName;
    }

    public ProjectEntity addProjectToUser(Long userId, ProjectEntity project) throws NotFoundException, AlreadyExistsException {
        UserEntity user = findUserById(userId);
        if (findProjectByName(user, project).isPresent()){
            throw new AlreadyExistsException("Project already exists!");
        }
        project.setUser(user);
        return project;
    }

}
