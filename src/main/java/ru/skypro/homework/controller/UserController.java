package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.models.User;
import ru.skypro.homework.service.UsersService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {

    private final UsersService userService;

    @Operation(
            summary = "Update password",
            description = "Update password"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    )
            })
    @PostMapping(
            value = "/set_password",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Void> setNewPassword(@Valid @RequestBody NewPasswordDTO newPasswordDTO) {
        userService.setPassword(newPasswordDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Get user",
            description = "Get user"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })
    @GetMapping(
            value = "/me",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<User> getUser(){
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Update user",
            description = "Update user"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })
    @PatchMapping(
            value = "/me",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<UpdateUserDTO> updateUser(@Valid @RequestBody UpdateUserDTO updateUserDTO){
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Update users image",
            description = "Update users image"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })
    @PatchMapping(
            value = "/me/image",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity<Void> updateUsersImage(@RequestPart("image") MultipartFile image){
        return ResponseEntity.ok().build();
    }
}

