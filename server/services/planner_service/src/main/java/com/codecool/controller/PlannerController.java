package com.codecool.controller;

import com.codecool.DTO.PlannerDTO.NewPlannerDTO;
import com.codecool.DTO.PlannerDTO.PlannerDTO;
import com.codecool.service.PlannerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planner")
public class PlannerController {
private final PlannerService plannerService;

public PlannerController(PlannerService plannerService) {
    this.plannerService = plannerService;
}

@GetMapping("/")
    public List<PlannerDTO> getAllPlanners() {
    return plannerService.findAll();
}

@GetMapping("/{id}")
    public PlannerDTO getPlannerById(@PathVariable Long id) {
    return  plannerService.findById(id);
}

@PostMapping("/")
    public long createPlanner(@RequestBody NewPlannerDTO plannerDTO) {
    return plannerService.createPlanner(plannerDTO);
}

@PatchMapping("/")
    public long updatePlanner(@RequestBody PlannerDTO plannerDTO) {
    return plannerService.updatePlanner(plannerDTO);
}

@DeleteMapping("/{id}")
    public boolean deletePlannerById(@PathVariable Long id) {
    return plannerService.deletePlanner(id);
}
}
