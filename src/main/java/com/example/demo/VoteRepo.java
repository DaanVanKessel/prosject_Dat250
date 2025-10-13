package com.example.demo;
import com.example.demo.entity.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VoteRepo extends CrudRepository<Vote, UUID> {
}
