package com.codecool.server.service;

import com.codecool.server.DTO.user.NewUserDTO;
import com.codecool.server.DTO.user.UserDTO;
import com.codecool.server.mapper.UserMapper;
import com.codecool.server.model.UserCheckRequest;
import com.codecool.server.model.UserEntity;
import com.codecool.server.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
private final UserRepository userRepository;
private final UserMapper userMapper=UserMapper.INSTANCE;
private final RabbitTemplate rabbitTemplate;
private final PasswordEncoder passwordEncoder;

@Autowired
public UserService(UserRepository userRepository, RabbitTemplate rabbitTemplate, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.rabbitTemplate = rabbitTemplate;
    this.passwordEncoder = passwordEncoder;

}
public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream().map(userMapper::userEntityToUserDTO).collect(Collectors.toList());
}

public UserDTO getUserById(long id) {
    return userRepository.findById(id).map(userMapper::userEntityToUserDTO).orElse(null);
}

public UserDTO getUserByEmail(String email) {
    UserEntity userEntity = userRepository.findByEmail(email);
    return userMapper.userEntityToUserDTO(userEntity);
}

public long createUser(NewUserDTO newUserDTO) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(newUserDTO.email());
    userEntity.setEmail(newUserDTO.email());
    userEntity.setPassword(passwordEncoder.encode(newUserDTO.password()));
    return userRepository.save(userEntity).getId();
}

public long modifyUser(UserDTO userDTO) {
    UserEntity userEntity = userRepository.findById(userDTO.id()).orElse(null);
    if (userEntity != null) {
        userEntity.setEmail(userDTO.email());
        userEntity.setPassword(userDTO.password());
        userEntity.setFirstName(userDTO.firstName());
        userEntity.setLastName(userDTO.lastName());
        userEntity.setUsername(userDTO.username());
        return userRepository.save(userEntity).getId();

    }
    return 0;
}



public boolean deleteUser(long id) {
    UserEntity userEntity = userRepository.findById(id).orElse(null);
    if (userEntity != null) {
        userRepository.delete(userEntity);
        return true;
    }
 return false;
}

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("userStringQueue", message);
    }

   @RabbitListener(queues = "authStringQueue")
 public void receiveMessage(String message) {
       System.out.println("Received message: " + message);

   }

    @RabbitListener(queues = "authRequestQueue")
    public void receiveUserCheckRequest(UserCheckRequest userCheckRequest) {
        System.out.println("Received user check request: " + userCheckRequest);
        boolean userExists = checkUserExists(userCheckRequest.getEmail());
        if (!userExists) {
            NewUserDTO newUserDTO= new NewUserDTO(userCheckRequest.getFirstName(), userCheckRequest.getLastName(), userCheckRequest.getEmail(), userCheckRequest.getPassword(),userCheckRequest.getChildrenId());

            createUser(newUserDTO);
            String response = userExists ? "User exists" : "User created";
            rabbitTemplate.convertAndSend("userStringQueue", response);        }

    }

    @RabbitListener(queues = "authUserQueue")
    public void receiveUserRequest(String email) {
    System.out.println("Received user request: " + email);
    boolean userExists = checkUserExists(email);
        System.out.println(userExists);
    if (userExists) {
        UserEntity user=userRepository.findByEmail(email);
        System.out.println(user.getEmail());
        rabbitTemplate.convertAndSend("userUserQueue", user);
        System.out.println("Ive sent the user: " + user.getEmail());
    }
    }


    private boolean checkUserExists(String email) {
        return userRepository.findByEmail(email)!=null;
    }


}
