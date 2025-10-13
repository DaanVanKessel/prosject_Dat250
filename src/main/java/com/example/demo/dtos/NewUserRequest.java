package com.example.demo.dtos;

public record NewUserRequest(
        String email,
        String username
) {
}