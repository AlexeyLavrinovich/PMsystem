package com.PMsystem.service;

import com.PMsystem.entity.ProjectEntity;
import com.PMsystem.entity.Role;
import com.PMsystem.entity.UserEntity;
import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.exception.NotFoundException;
import com.PMsystem.model.User;
import com.PMsystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(UserEntity user) throws AlreadyExistsException {
        UserEntity userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null){
            throw new AlreadyExistsException("User with this username already exists! Try something else!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.ROLE_USER));
        user.setIsAdmin(false);
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

    public void addRole(Long id) throws NotFoundException, AlreadyExistsException {
        UserEntity user = findUserById(id);
        if (user.getIsAdmin()) {
            throw new AlreadyExistsException("Admin status already set");
        }
        user.setIsAdmin(true);
        Set<Role> roles = user.getRoles();
        roles.add(Role.ROLE_ADMIN);
        user.setRoles(roles);
        userRepo.save(user);
    }

    public void deleteUser(Long id) throws NotFoundException {
        userRepo.delete(findUserById(id));
    }

    public Optional<ProjectEntity> findProjectByName(UserEntity user, ProjectEntity project) {
        Optional<ProjectEntity> sameProjectName = user.getProjects().stream()
                .filter(pr -> pr.getName().equals(project.getName()))
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public void updateEncode(UserEntity userEntity){
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepo.save(userEntity);
    }
}
