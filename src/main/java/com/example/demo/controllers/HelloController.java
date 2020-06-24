package com.example.demo.controllers;

import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.dao.Greeting;
import com.example.demo.dao.User;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index() {
			return "Greetings from Spring Boot!";
	}

	@GetMapping("/users/{email}")
  User getByEmail(@PathVariable String email) {
    return userService.getByEmail(email);
	}

	@PostMapping("/users")
  User newEmployee(@RequestBody User newUser) {
		User userSaved = userService.register(newUser);
    return userSaved;
  }

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}