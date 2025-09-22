package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import java.time.Instant;

import java.time.temporal.ChronoUnit;

public class Poll {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String question;

    @Getter
    private User puplisher;

    @Getter
    @Setter
    private List<VoteOption> options;

    @Getter
    @Setter
    private Instant publishedAt;
    @Getter
    @Setter
    private Instant validUntil;

    public Poll(int id, String question, User puplisher, int validUntil){
        this.id = id;
        this.question = question;
        this.options = new ArrayList<>();
        this.publishedAt = Instant.now();
        this.validUntil = Instant.now().plus(validUntil, ChronoUnit.MINUTES);
        this.puplisher = puplisher;
        puplisher.getCreatedPolls().add(this);
    }

    @Override
    public String toString(){
        return String.format("%s published: %s valid until: %s", question, publishedAt.toString(), validUntil.toString());
    }

}
