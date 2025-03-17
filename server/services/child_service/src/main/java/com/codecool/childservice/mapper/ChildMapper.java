package com.codecool.childservice.mapper;


import com.codecool.childservice.ChildDTO.ChildDTO;
import com.codecool.childservice.ChildDTO.NewChildDTO;
import com.codecool.childservice.model.Child;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChildMapper {
    ChildMapper INSTANCE = Mappers.getMapper(ChildMapper.class);
ChildDTO childToChildDTO(Child child);
Child childDTOToChild(ChildDTO childDTO);
Child newChildDTOtoChild(NewChildDTO newChildDTO);

}
