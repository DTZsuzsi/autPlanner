package com.codecool.childservice.service;

import com.codecool.childservice.ChildDTO.ChildDTO;
import com.codecool.childservice.mapper.ChildMapper;
import com.codecool.childservice.model.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codecool.childservice.repository.ChildRepository;
import com.codecool.childservice.ChildDTO.NewChildDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildService {
private final ChildRepository childRepository;
private final ChildMapper childMapper=ChildMapper.INSTANCE;

@Autowired
    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public List<ChildDTO> getChildren(String parentEmail) {
    List<Child> children =childRepository.findChildrenByParentEmail(parentEmail);
    return  children.stream().map(childMapper::childToChildDTO).collect(Collectors.toList());

    }

    public ChildDTO getChildById(long id) {
    Child child = childRepository.findById(id).orElse(null);
    return childMapper.childToChildDTO(child);
    }

    public long addChild(NewChildDTO newChildDTO) {
Child newChild =childMapper.newChildDTOtoChild(newChildDTO);
return childRepository.save(newChild).getId();
    }

    public boolean deleteChildById(long id) {
    childRepository.deleteById(id);
    return childRepository.existsById(id);
    }

}
