package com.example.demo;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
static class RepositoryComponent {

}
public class Repository {
    @Getter
    private HashMap<Integer,User> users;
    @Getter
    private HashMap<Integer, Poll> polls;

    public Repository(){
        this.users = new HashMap<>();
        this.polls = new HashMap<>();

        User u1 = new User("silje", "silje@uib.no");
        this.users.put(this.users.size(), u1);
        User u2 = new User("test", "test@uib.no");
        this.users.put(this.users.size(), u2);

        Poll p1 = new Poll(1, "what to do today?", u1,2);
        VoteOption o1 = new VoteOption("chill", p1);
        VoteOption o2 = new VoteOption("go to the beach", p1);
        new Vote(u1, o2);
        new Vote(u2, o2);

        this.polls.put(this.polls.size() + 1, p1);
    }



}
