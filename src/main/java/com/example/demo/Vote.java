package com.example.demo;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

public class Vote {
    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private VoteOption option;

    private Instant publishedAt;

    public Vote(User user, VoteOption option) {
        this.user = user;
        this.option = option;
        this.user.getUserVotes().add(this);
        this.option.getVotes().add(this);

        this.publishedAt = Instant.now();
    }

}
