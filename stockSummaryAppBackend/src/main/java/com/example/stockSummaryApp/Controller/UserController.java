package com.example.stockSummaryApp.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public List<String> getUsers() {
        return List.of("Kevin", "Alice", "Bob");
    }

    @PostMapping
    public String createUser(@RequestBody String name) {
        return name + " created";
    }
}
