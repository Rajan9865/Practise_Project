package com.usermanagement.user_management_system.controller;

import com.usermanagement.user_management_system.dto.UserRequestDto;
import com.usermanagement.user_management_system.dto.UserResponseDto;
import com.usermanagement.user_management_system.service.UserService;
import com.usermanagement.user_management_system.util.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@SecurityRequirement(name="basicAuth")
@Tag(name = "User API",description = "Operations related to User Management")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Create User
     */
    @PostMapping
    @Operation( summary = "Create User",description = "Creates a new user in the system.")
    @ApiResponses({@ApiResponse( responseCode = "201",description = "User created successfullyhhh"),
            @ApiResponse(responseCode = "400",description = "Validation failed"),
            @ApiResponse(responseCode = "409",description = "Username or email already exists")
    })
    public ResponseEntity<ApiResult<UserResponseDto>> createUser(
            @Valid @RequestBody UserRequestDto request) {
        UserResponseDto response = userService.createUser(request);
        ApiResult<UserResponseDto> apiResponse = ApiResult.<UserResponseDto>builder()
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

    @Operation(summary = "Get User By Id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<UserResponseDto>> getUserById(
            @PathVariable Long id) {
        UserResponseDto response = userService.getUserById(id);
        ApiResult<UserResponseDto> apiResponse = ApiResult.<UserResponseDto>builder()
                .success(true)
                .message("User fetched successfully.")
                .data(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get All Users
     */
//    @Operation(summary = "Get All Users")
//    @GetMapping
//    public ResponseEntity<ApiResult<List<UserResponseDto>>> getAllUsers() {
//        List<UserResponseDto> response = userService.getAllUsers();
//        ApiResult<List<UserResponseDto>> apiResponse =
//                ApiResult.<List<UserResponseDto>>builder()
//                        .success(true)
//                        .message("Users fetched successfully.")
//                        .data(response)
//                        .build();
//        return ResponseEntity.ok(apiResponse);
//    }
    @Operation(summary = "get all users")
    @GetMapping
    public ResponseEntity<ApiResult<Page<UserResponseDto>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        Page<UserResponseDto> users = userService.getallUsers(pageable);
        ApiResult<Page<UserResponseDto>> response = ApiResult.<Page<UserResponseDto>>builder()
                .success(true)
                .message("Users fetched successfully.")
                .data(users)
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     * Update User
     */
    @Operation(summary = "Update User")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResult<UserResponseDto>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDto request) {
        UserResponseDto response = userService.updateUser(id, request);
        ApiResult<UserResponseDto> apiResponse = ApiResult.<UserResponseDto>builder()
                .success(true)
                .message("User updated successfully.")
                .data(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Delete User
     */
    @Operation(summary = "Delete User")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     *
     * @param username
     * @return Search user
     */
    @Operation(summary = "search users")
    @GetMapping("search")
    public ResponseEntity<ApiResult<List<UserResponseDto>>> searchUsers(
            @RequestParam String username
    ) {
        List<UserResponseDto> user = userService.searchUsers(username);
        ApiResult<List<UserResponseDto>> response = ApiResult.<List<UserResponseDto>>builder()
                .success(true)
                .message("Users fetched successfully.")
                .data(user)
                .build();
        return ResponseEntity.ok(response);
    }
}