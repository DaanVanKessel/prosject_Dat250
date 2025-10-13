package com.example.demo.dtos;
import java.util.List;

public record NewPollRequest(
        String question,
        Long createdBy,
        List<String> options
) {
}