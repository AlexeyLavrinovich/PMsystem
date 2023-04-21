package com.PMsystem.model;

import com.PMsystem.entity.ProjectEntity;
import com.PMsystem.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {

    private Long id;
    private String name;
    private String description;
    private String creator;

    private List<Task> tasks;

    public static Project toModel(ProjectEntity entity) {
        Project model = new Project();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setCreator(entity.getUser().getUsername());
        model.setTasks(entity.getTasks().stream().map(Task::toModel).collect(Collectors.toList()));
        return model;
    }
}
