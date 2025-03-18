package com.codecool.server.DTO.user;

import java.util.List;

public record UserDTO(long id, String username, String password, String firstName, String lastName, String email, List<Long> childrenId) {
}
