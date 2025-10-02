package com.example.demo;

import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@CrossOrigin
public class PollController {

    private Repository repository;

    public  PollController(@Autowired Repository repository) {
        this.repository = repository;
    }


/*
    public PollController(@Autowired Repository repository) {
        this.repository = repository;
    }

    @GetMapping("/polls")
    public HashMap<Poll, List<String>> getPolls(){
        return repository.getPolls();
    }
    @GetMapping("/users")
    public HashMap<Integer, User> getUsers(){
        return repository.getUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        repository.getUsers().put(getUsers().size(),user);
        return ResponseEntity.created(URI.create("/users/" + user)).body(user);
    }

    @PostMapping("/polls")
    public ResponseEntity<Poll> addPoll(@RequestParam(name = "question") String question, @RequestParam(name = "validUntil") int validUntil, @RequestBody ArrayList<String> options){
        Poll poll = new Poll(question,validUntil);
        repository.getPolls().put(poll, options);
        return ResponseEntity.created(URI.create("/polls/" + question)).body(poll);
    }

    @DeleteMapping("/polls")
    public void deletePoll(@RequestBody Poll poll){
        repository.getPolls().remove(poll);
    }

    @GetMapping("users/{user}/vote")
    public VoteOption getUserVote( @PathVariable String user){
        return repository.getVotes().get(user);
    }

    @PostMapping("/users/{username}/vote")
    public ResponseEntity<VoteOption> addVote( @PathVariable String username, @RequestBody VoteOption vote){
        repository.getVotes().put(username, vote);
        return ResponseEntity.ok().body(vote);
    }*/
}
