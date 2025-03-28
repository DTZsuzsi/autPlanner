package com.codecool.service;

import NewTaskDTO;
import com.codecool.DTO.TaskDTO.TaskDTO;
import com.codecool.model.Task;
import com.codecool.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
TaskRepository repository;

@Autowired
public TaskService(TaskRepository repository) {
    this.repository = repository;
}

    public TaskDTO getTaskById(Long id) {
        Task task = repository.findById(id).orElse(null);
        return new TaskDTO(task.getName(), task.getDescription(), task.getImageUrl(), task.getId());
    }

    public TaskDTO getTaskByName(String name) {
    Task task=repository.findTaskByName(name);
    return new TaskDTO(task.getName(), task.getDescription(), task.getImageUrl(), task.getId());
    }
    public List<TaskDTO> getAllTasks() {
    List<Task> tasks = repository.findAll();
   return tasks.stream().map(task -> new TaskDTO(task.getName(), task.getDescription(), task.getImageUrl(), task.getId())).collect(Collectors.toList());
    }

    public long createTask(NewTaskDTO taskDTO) {
    Task task = new Task(taskDTO.name(), taskDTO.description(), taskDTO.imageUrl());
    return repository.save(task).getId();
    }

    public long updateTask(TaskDTO taskDTO) {
    Task task=repository.findById(taskDTO.id()).orElse(null);
    task.setName(taskDTO.name());
    task.setDescription(taskDTO.description());
    task.setImageUrl(taskDTO.imageUrl());
    return repository.save(task).getId();
    }

    public boolean deleteTaskById(Long id) {
    repository.deleteById(id);
    return repository.existsById(id);
    }
}
