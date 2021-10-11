package com.example.ResourceServer.controllers;

import com.example.ResourceServer.model.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status/check")
    public String status(){
        return "Working...";
    }

    @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
    @Secured("ROLE_developer")
    @DeleteMapping(path="/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return "Deleted user with id: " + id + " and a subject: " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return new User("Galina", "Gryaznova","1a8b0f4f-131f-4b9f-9250-9b8b7dfd6513");
    }

}
