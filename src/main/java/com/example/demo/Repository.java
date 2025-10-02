package com.example.demo;

import com.example.demo.entity.Poll;
import com.example.demo.entity.User;
import com.example.demo.entity.Vote;
import com.example.demo.entity.VoteOption;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
public class Repository {
    @Getter
    private final HashMap<Integer, User> users;
    @Getter
    private final HashMap<Integer, Poll> polls;

    public Repository(){
        this.users = new HashMap<>();
        this.polls = new HashMap<>();

        User u1 = new User("silje", "silje@uib.no");
        this.users.put(this.users.size(), u1);
        User u2 = new User("test", "test@uib.no");
        this.users.put(this.users.size(), u2);

        Poll p1 = new Poll("what to do today?", u1);
        VoteOption o1 = new VoteOption("chill", p1, 1);
        VoteOption o2 = new VoteOption("go to the beach", p1, 2);
        new Vote(u1, o2);
        new Vote(u2, o2);

        this.polls.put(this.polls.size() + 1, p1);
    }



}
