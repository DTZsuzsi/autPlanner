package com.codecool.childservice.controller;

import com.codecool.childservice.ChildDTO.ChildDTO;
import com.codecool.childservice.ChildDTO.NewChildDTO;
import com.codecool.childservice.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/child")
public class ChildController {
    private final ChildService childService;

    @Autowired
    public ChildController(ChildService childService) {
        this.childService = childService;
    }

@GetMapping("/{id}")
    public ChildDTO getChildById(@RequestParam("id") long id) {
    return childService.getChildById(id);
}

@GetMapping("/parent/{email}")
    public List<ChildDTO> getChildByParentEmail(@PathVariable("email") String email) {
        return childService.getChildren(email);
}

@PostMapping
    public long addChild(@RequestBody NewChildDTO newChildDTO) {
        return childService.addChild(newChildDTO);
}

@DeleteMapping("/{id}")
    public boolean deleteChildById(@RequestParam("id") long id) {
        return childService.deleteChildById(id);
}

}
