package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;


    private String email;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<Poll> createdPolls;

    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL)
    private Collection<Vote> userVotes;

    /**
     * Creates a new Poll object for this user
     * with the given poll question
     * and returns it.
     */
    public Poll createPoll(String question) {
        Poll poll = new Poll(question, this);
        createdPolls.add(poll);
        return poll;
    }

    /**
     * Creates a new Vote for a given VoteOption in a Poll
     * and returns the Vote as an object.
     */
    public Vote voteFor(VoteOption option) {
        return option.addVote(this);
    }

    public User(String username, String email){
        this.username = username;
        this.email = email;
        this.createdPolls = new ArrayList<>();
        this.userVotes = new HashSet<>();
    }

    public User(){
        this.createdPolls = new ArrayList<>();
    }

}
