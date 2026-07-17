package com.usermanagement.user_management_system.controller;

import com.usermanagement.user_management_system.dto.UserRequestDto;
import com.usermanagement.user_management_system.dto.UserResponseDto;
import com.usermanagement.user_management_system.service.UserService;
import com.usermanagement.user_management_system.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lenovo
 * @version 1.0
 * Practise_Project
 * @since 7/16/2026
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Create User
     */
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> createUser(
            @Valid @RequestBody UserRequestDto request) {
        UserResponseDto response = userService.createUser(request);
        ApiResponse<UserResponseDto> apiResponse = ApiResponse.<UserResponseDto>builder()
                .success(true)
                .message("User created successfully.")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiResponse);
    }

    /**
     * Get User By Id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(
            @PathVariable Long id) {
        UserResponseDto response = userService.getUserById(id);
        ApiResponse<UserResponseDto> apiResponse = ApiResponse.<UserResponseDto>builder()
                .success(true)
                .message("User fetched successfully.")
                .data(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get All Users
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {
        List<UserResponseDto> response = userService.getAllUsers();
        ApiResponse<List<UserResponseDto>> apiResponse =
                ApiResponse.<List<UserResponseDto>>builder()
                        .success(true)
                        .message("Users fetched successfully.")
                        .data(response)
                        .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Update User
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDto request) {
        UserResponseDto response = userService.updateUser(id, request);
        ApiResponse<UserResponseDto> apiResponse = ApiResponse.<UserResponseDto>builder()
                .success(true)
                .message("User updated successfully.")
                .data(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Delete User
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}