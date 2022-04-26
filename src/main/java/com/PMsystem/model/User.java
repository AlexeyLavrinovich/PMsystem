package com.PMsystem.model;

import com.PMsystem.entity.Role;
import com.PMsystem.entity.UserEntity;

public class User {
    private Long id;
    private String username;
    private Boolean isAdmin;
    private Role role;

    public static User toModel(UserEntity entity){
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setAdmin(entity.getAdmin());
        model.setRole(entity.getRole());
        return model;
    }

    public User() {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
