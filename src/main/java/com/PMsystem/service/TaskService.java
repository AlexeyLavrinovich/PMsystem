package com.PMsystem.service;

import com.PMsystem.entity.ProjectEntity;
import com.PMsystem.entity.TaskEntity;
import com.PMsystem.exception.NotFoundException;
import com.PMsystem.model.Task;
import com.PMsystem.repository.ProjectRepo;
import com.PMsystem.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private ProjectRepo projectRepo;

    public Page<Task> projectTasks(
            Long projectId,
            Optional<Integer> page,
            Optional<Integer> size,
            Optional<String> sortBy
    ) {
        return taskRepo.findByProjectId(
                projectId,
                PageRequest.of(
                        page.orElse(0),
                        size.orElse(5),
                        Sort.Direction.DESC, sortBy.orElse("id")
                )).map(Task::toModel);
    }

    public void addTask(Long projectId, TaskEntity task) throws NotFoundException {
        ProjectEntity projectFromDb = findProjectById(projectId);
        task.setProject(projectFromDb);
        taskRepo.save(task);
    }

    private ProjectEntity findProjectById(Long projectId) throws NotFoundException {
        Optional<ProjectEntity> projectFromDb = projectRepo.findById(projectId);
        if(projectFromDb.isEmpty()){
            throw new NotFoundException("Can't find project!");
        }
        return projectFromDb.get();
    }

    public void updateTask(Long projectId, Long id, TaskEntity newTask) throws NotFoundException {
        ProjectEntity projectFromDb = findProjectById(projectId);
        TaskEntity task = findTaskById(id);
        task.setTask(newTask.getTask());
        taskRepo.save(task);
    }

    private TaskEntity findTaskById(Long id) throws NotFoundException {
        Optional<TaskEntity> task = taskRepo.findById(id);
        if (task.isEmpty()){
            throw new NotFoundException("Can't find task!");
        }
        return task.get();
    }

    public void deleteTask(Long projectId, Long id) throws NotFoundException {
        findProjectById(projectId);
        TaskEntity task = findTaskById(id);
        task.setDeleted(true);
        taskRepo.save(task);
    }
}
