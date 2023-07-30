package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateUserDTO {

    @Size.List
            ({
                    @Size(min = 3, message = "Firstname to short"),
                    @Size(max = 10, message = "Firstname to long")
            })
    private String lastName;

    @Size.List
            ({
                    @Size(min = 3, message = "Lastname to short"),
                    @Size(max = 10, message = "Lastname to long")
            })
    private String firstName;

    @Pattern
            (
                    regexp = "\\+7s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
                    message = "Enter the number in the format +71234567890 or +7(123)456-78-90"
            )
    private String phone;
}
