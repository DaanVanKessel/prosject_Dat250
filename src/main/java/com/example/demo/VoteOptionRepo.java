package com.example.demo;

import com.example.demo.entity.VoteOption;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VoteOptionRepo extends CrudRepository<VoteOption, UUID> {
}