package com.PMsystem.model;

import com.PMsystem.entity.TaskEntity;

public class Task {

    private Long id;
    private String task;

    public static Task toModel(TaskEntity entity) {
        Task model = new Task();
        model.setId(entity.getId());
        model.setTask(entity.getTask());
        return model;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
