package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private Repository repository;

    public UserController(@Autowired Repository repository) {
        this.repository = repository;
    }

    public List<User> getUsers(){
        return new ArrayList<>(this.repository.getUsers().values());
    }

}

