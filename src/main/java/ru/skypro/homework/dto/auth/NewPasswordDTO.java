package ru.skypro.homework.dto.auth;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class NewPasswordDTO {

    @Size.List
            ({
                    @Size(min = 8, message = "password to short"),
                    @Size(max = 16, message = "password to long")
            })
    private String currentPassword;

    @Size.List
            ({
                    @Size(min = 8, message = "password to short"),
                    @Size(max = 16, message = "password to long")
            })
    private String newPassword;
}
