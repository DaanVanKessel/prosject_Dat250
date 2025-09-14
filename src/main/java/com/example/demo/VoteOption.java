package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

public class VoteOption {
    @Getter
    @Setter
    private String caption;
    @Getter
    @Setter
    private Poll poll;
    @Getter
    @Setter
    private Collection<Vote> votes;

    public VoteOption(String caption, Poll poll){
        this.caption = caption;
        this.poll = poll;
        poll.getOptions().add(this);
        this.votes = new ArrayList<>();
    }

}
