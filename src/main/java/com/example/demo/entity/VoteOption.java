package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
public class VoteOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption;

    private int presentationOrder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    private Collection<Vote> votes;

    public VoteOption(String caption, Poll poll, int presentationOrder){
        this.caption = caption;
        this.poll = poll;
        this.presentationOrder = presentationOrder;
        this.votes = new ArrayList<>();
    }
    public VoteOption(){

    }

    public Vote addVote(User voter){
        Vote vote = new Vote(voter, this);
        votes.add(vote);
        return vote;
    }
}
