package com.codecool.service;

import com.codecool.DTO.PlannerDTO.NewPlannerDTO;
import com.codecool.DTO.PlannerDTO.PlannerDTO;
import com.codecool.DTO.TaskDTO.TaskDTO;
import com.codecool.model.Planner;
import com.codecool.model.Task;
import com.codecool.repository.PlannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlannerService {
    PlannerRepository repository;

    @Autowired
    public PlannerService(PlannerRepository repository) {
        this.repository = repository;
    }

    public List<PlannerDTO> findAll() {
        List<Planner> planners = repository.findAll();
        return planners.stream()
                .map(planner -> new PlannerDTO(
                        planner.getName(),
                        planner.getChildName(),
                        planner.getId(),
                        planner.getTasks().stream()
                                .map(task -> new TaskDTO(
                                        task.getName(),
                                        task.getDescription(),
                                        task.getImageUrl(),
                                        task.getId()))
                                .toList()
                )).toList();
    }

    public PlannerDTO findById(Long id) {
        Planner planner = repository.findById(id).orElse(null);
        List<Task> tasks = planner.getTasks();
        List<TaskDTO> taskDTOS = tasks.stream().map(task -> new TaskDTO(task.getName(), task.getDescription(), task.getImageUrl(), task.getId())).toList();
        return new PlannerDTO(planner.getName(), planner.getChildName(), planner.getId(), taskDTOS);
    }

public long createPlanner(NewPlannerDTO newPlannerDTO) {
        Planner newPlanner = new Planner();
        newPlanner.setName(newPlannerDTO.name());
        newPlanner.setChildName(newPlannerDTO.childName());
        return repository.save(newPlanner).getId();
}

public boolean deletePlanner(Long id) {
        repository.deleteById(id);
        return repository.existsById(id);
}

public long updatePlanner(PlannerDTO plannerDTO) {
        Planner planner=repository.findById(plannerDTO.id()).orElse(null);
        planner.setName(plannerDTO.name());
        planner.setChildName(plannerDTO.childName());
        List<TaskDTO> taskDTOs=plannerDTO.tasks();

        if (taskDTOs!=null) {
        List <Task> tasks=taskDTOs.stream().map(taskDTO -> new Task(taskDTO.name(), taskDTO.description(),
                taskDTO.imageUrl(),taskDTO.id())).toList();
        planner.setTasks(tasks);}
        return repository.save(planner).getId();
}
}
