package com.codecool.childservice.ChildDTO;

import java.time.LocalDate;

public record ChildDTO(String firstName, String lastName, String parentEmail, LocalDate birthDay, int Age, String gender, long id) {
}
