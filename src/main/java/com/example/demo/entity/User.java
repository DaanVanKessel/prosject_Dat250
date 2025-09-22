package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;



public class User {


    @Getter
    private String username;

    @Getter
    private String email;

    @Getter
    private List<Poll> createdPolls;

    @Getter
    private Collection<Vote> userVotes;



    public User(String username, String email){
        this.username = username;
        this.email = email;
        this.createdPolls = new ArrayList<>();
        this.userVotes = new HashSet<>();
    }



}
