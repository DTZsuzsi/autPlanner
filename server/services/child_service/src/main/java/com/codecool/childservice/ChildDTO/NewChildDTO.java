package com.codecool.childservice.ChildDTO;

import java.time.LocalDate;

public record NewChildDTO(String firstName, String lastName, String parentEmail, LocalDate birthDay,String gender) {
}
