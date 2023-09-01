package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.models.User;
import ru.skypro.homework.service.FileService;
import ru.skypro.homework.service.UsersService;
import ru.skypro.homework.service.impl.FileServiceImpl;

import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
@CrossOrigin(value = "http://localhost:3000")
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
    public ResponseEntity<HttpStatus> setNewPassword(@Valid @RequestBody NewPasswordDTO newPasswordDTO) {
        log.info("{}", ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        userService.updateUserPassword(newPasswordDTO, ((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()));
        return ResponseEntity.ok(HttpStatus.OK);
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
    public ResponseEntity<UserDTO> getUser() {
        return ResponseEntity.ok(userService.getUser((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()));
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
    public ResponseEntity<HttpStatus> updateUser(@Valid @RequestBody UpdateUserDTO updateUserDTO) {
        userService.editUser((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal(), updateUserDTO);
        return ResponseEntity.ok(HttpStatus.OK);
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
    public ResponseEntity<HttpStatus> updateUsersImage(@RequestPart("image") MultipartFile image) {
        userService.editImage((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal(), image);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}



