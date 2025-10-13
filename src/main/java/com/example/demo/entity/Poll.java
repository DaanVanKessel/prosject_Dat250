package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import java.time.Instant;

import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Entity
@Table(name= "polls")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String question;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User publisher;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<VoteOption> options;


    private Instant publishedAt;

    private Instant validUntil;

    public Poll(String question, User publisher){
        this.question = question;
        this.options = new ArrayList<>();
        this.publishedAt = Instant.now();
        this.publisher = publisher;
    }

    public Poll(){
        this.publishedAt = Instant.now();


    }
    public VoteOption addVoteOption(String caption){
        VoteOption option = new VoteOption(caption,this, options.size());
        options.add(option);
        return option;
    }


    @Override
    public String toString(){
        return String.format("%s published: %s valid until: %s", question, publishedAt.toString(), validUntil.toString());
    }

}
