package com.example.demo;

import com.example.demo.dtos.ErrorMsg;
import com.example.demo.dtos.NewPollRequest;
import com.example.demo.entity.Poll;
import com.example.demo.entity.User;
import com.example.demo.entity.Vote;
import com.example.demo.entity.VoteOption;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@RequestMapping("/polls")
@RestController
public class PollsController {

    private static final String EXCHANGE_NAME = "direct_Polls";

    @Autowired
    private PollsRepo repo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VoteOptionRepo voteOptionRepo;

    @Autowired
    private VoteRepo voteRepo;

    @GetMapping()
    public Iterable<Poll> allPolls() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Poll> pollById( @PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<?> createPoll(@RequestBody NewPollRequest request) {
        Optional<User> maybeUser = userRepo.findById(request.createdBy());

        if (maybeUser.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorMsg("User with id '" + request.createdBy() + "' does not exist"));
        }
        User u = maybeUser.get();
        Poll p = u.createPoll(request.question());
        List<VoteOption> options = new ArrayList<>();
        for (String o : request.options()) {
            options.add(p.addVoteOption(o));
        }
        p = repo.save(p);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            ObjectMapper objectMapper = new ObjectMapper();
            String jasonPoll = objectMapper.writeValueAsString(p);
            channel.basicPublish(EXCHANGE_NAME, p.getId().toString() , null, jasonPoll.getBytes("UTF-8"));
        } catch (TimeoutException | IOException e) {
            throw new RuntimeException(e);
        }


        return ResponseEntity.created(URI.create("/polls/" + p.getId())).body(p);
    }


    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        if (repo.findById(pollId).isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorMsg("Poll with id '" + pollId + "' does not exist"));
        }
        repo.deleteById(pollId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/votes")
    @Transactional
    public ResponseEntity<?> createPoll(@PathVariable Long id, @RequestParam Integer option, @RequestParam Long userId) {
        Optional<Poll> maybePoll = repo.findById(id);
        if (maybePoll.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorMsg("Poll with id '" + id + "' does not exist"));
        }
        Optional<User> maybeUser = userRepo.findById(userId);
        if (maybeUser.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorMsg("User with id '" + userId + "' does not exist"));
        }
        Poll poll = maybePoll.get();
        if (option < 0 || option >= poll.getOptions().size()) {
            return ResponseEntity.badRequest().body(new ErrorMsg("Poll with id  '" + id + "' does not have an option with index/order '" + option + "'!"));
        }
        VoteOption voteOption = poll.getOptions().get(option);
        Vote vote = maybeUser.get().voteFor(voteOption);
        voteRepo.save(vote);
        return ResponseEntity.ok().build();
    }


}
/*
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

//@Controller
//@CrossOrigin
@RequestMapping("/poll")
@RestController
public class PollController {

    private Repository repository;

    public  PollController(@Autowired Repository repository) {
        this.repository = repository;
    }



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
    }
}*/
