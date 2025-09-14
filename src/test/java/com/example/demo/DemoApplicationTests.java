package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {




    @Test
    public void testGetPredefinedLocations(@Autowired TestRestTemplate restTemplate) throws Exception {
        ResponseEntity<User> entity = restTemplate.getForEntity(URI.create("/Users"), User.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(new User("test","test@uib.no"), entity.getBody());

        entity = restTemplate.getForEntity(URI.create("/Users/bob"), User.class);
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());

    }

    @Test
    public void testPostUser(@Autowired TestRestTemplate restTemplate) throws Exception {
        ResponseEntity<User> entity = restTemplate.postForEntity(URI.create("/User"), new User("test1","test1@uib.no") ,User.class);
        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        assertEquals(new User("test1","test1@uib.no"), entity.getBody());

        entity = restTemplate.getForEntity(URI.create("/users/test1"), User.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(new User("test1","test1@uib.no"), entity.getBody());


    }

}