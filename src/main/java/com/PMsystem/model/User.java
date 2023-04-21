package com.PMsystem.model;

import com.PMsystem.entity.Role;
import com.PMsystem.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
        model.setIsAdmin(entity.getIsAdmin());
        model.setRoles(entity.getRoles());
        model.setProjects(entity.getProjects().stream().map(Project::toModel).collect(Collectors.toList()));
        return model;
    }
}
