package com.example.demo;


import com.example.demo.entity.Poll;
    import org.springframework.data.repository.CrudRepository;

public interface PollsRepo extends CrudRepository<Poll, Long> {
}