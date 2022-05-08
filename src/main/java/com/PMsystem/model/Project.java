package com.PMsystem.model;

import com.PMsystem.entity.ProjectEntity;
import com.PMsystem.entity.TaskEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Project {

    private Long id;
    private String name;
    private String description;

    private List<Task> tasks;

    public static Project toModel(ProjectEntity entity) {
        Project model = new Project();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setTasks(entity.getTasks().stream().map(Task::toModel).collect(Collectors.toList()));
        return model;
    }

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
