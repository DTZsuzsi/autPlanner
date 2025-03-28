package com.codecool.controller;

import NewTaskDTO;
import com.codecool.DTO.TaskDTO.TaskDTO;
import com.codecool.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

private final TaskService taskService;

@Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<TaskDTO> getTasks() {
    return taskService.getAllTasks();
    }

    @GetMapping("/{taskId}")
    public TaskDTO getTask(@PathVariable long taskId) {
    return taskService.getTaskById(taskId);
    }

    @GetMapping("/name/{name}")
    public TaskDTO getTaskByName(@PathVariable String name) {
    return taskService.getTaskByName(name);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTask(@PathVariable long id) {
   return taskService.deleteTaskById(id);
    }

    @PostMapping("/")
    public long createTask(@RequestBody NewTaskDTO taskDTO) {
    return taskService.createTask(taskDTO);
    }

    @PatchMapping("/")
    public long updateTask(@RequestBody TaskDTO taskDTO) {
    return taskService.updateTask(taskDTO);
    }
}
