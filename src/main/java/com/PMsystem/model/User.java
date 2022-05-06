package com.PMsystem.model;

import com.PMsystem.entity.Role;
import com.PMsystem.entity.UserEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class User {
    private Long id;
    private String username;
    private Boolean isAdmin;
    private Set<Role> roles;
    private List<Project> projects;

    public static User toModel(UserEntity entity){
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setAdmin(entity.getAdmin());
        model.setRoles(entity.getRoles());
        model.setProjects(entity.getProjects().stream().map(Project::toModel).collect(Collectors.toList()));
        return model;
    }

    public User() {
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
