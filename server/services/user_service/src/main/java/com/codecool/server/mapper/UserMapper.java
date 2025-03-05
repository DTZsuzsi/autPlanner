package com.codecool.server.mapper;

import com.codecool.server.DTO.user.NewUserDTO;
import com.codecool.server.DTO.user.UserDTO;
import com.codecool.server.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

UserDTO userEntityToUserDTO(UserEntity userEntity);
UserEntity userDTOToUserEntity(UserDTO user);
UserEntity newUserDTOToEntity(NewUserDTO newUserDTO);
}
