package com.codecool.DTO.PlannerDTO;

import com.codecool.DTO.TaskDTO.TaskDTO;

import java.util.List;

public record PlannerDTO(String name, String childName, long id, List<TaskDTO> tasks) {
}
