package com.usermanagement.user_management_system.service.impl;

import com.usermanagement.user_management_system.dto.UserRequestDto;
import com.usermanagement.user_management_system.dto.UserResponseDto;
import com.usermanagement.user_management_system.entity.User;
import com.usermanagement.user_management_system.exception.InvalidUserException;
import com.usermanagement.user_management_system.exception.UserNotFoundException;
import com.usermanagement.user_management_system.repository.UserRepository;
import com.usermanagement.user_management_system.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lenovo
 * @version 1.0
 * Practise_Project
 * @since 7/15/2026
 */
@Service
@Profile("db")
public class DatabaseUserService implements UserService {

    private final UserRepository repository;
    private final ModelMapper mapper;

    public DatabaseUserService(UserRepository repository,
                               ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto request) {
        if (repository.existsByUsername(request.getUsername())) {
            throw new InvalidUserException("Username already exists.");
        }
        if (repository.existsByEmail(request.getEmail())) {
            throw new InvalidUserException("Email already exists.");
        }
        User user = mapper.map(request, User.class);
        User savedUser = repository.save(user);
        return mapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id : " + id));
        return mapper.map(user, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return repository.findAll().stream().map(
                user -> mapper.map(user, UserResponseDto.class)
        ).toList();
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto request) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id : " + id));
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        User updated = repository.save(user);
        return mapper.map(updated, UserResponseDto.class);
    }

    @Override
    public void deleteUser(Long id) {
    repository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found with id : " + id));
    repository.deleteById(id);
    }
}
