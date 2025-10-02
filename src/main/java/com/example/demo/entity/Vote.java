package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "voter_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "option_id")
    private VoteOption option;

    private Instant publishedAt;

    public Vote(User user, VoteOption option) {
        this.user = user;
        this.option = option;
        this.publishedAt = Instant.now();
    }

    public Vote(){

    }
}
