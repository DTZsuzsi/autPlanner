package com.codecool.server.DTO.user;

public record UserDTO(long id, String username, String password, String firstName, String lastName, String email) {
}
