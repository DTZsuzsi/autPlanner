package com.codecool.server.DTO.user;

import java.util.List;

public record NewUserDTO(String firstName, String lastName, String email, String password, List<Long> childrenId) {
}
