package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String regDate;
    private String phone;
    private String role;
    private String city;
    private String image;
}