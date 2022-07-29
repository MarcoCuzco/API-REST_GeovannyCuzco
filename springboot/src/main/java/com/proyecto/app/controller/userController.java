package com.proyecto.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.app.model.User;
import com.proyecto.app.service.userService;


@RestController
@RequestMapping("/api/users")
public class userController {
	@Autowired
	private userService service;
	
	@PostMapping
	public ResponseEntity<?> create (@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> read (@PathVariable Long id){
		Optional<User> oUser = service.findById(id);
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(service.findById(id));
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id){
		Optional<User> userU = service.findById(id);
		if(!userU.isPresent()) {
			ResponseEntity.notFound().build();
		}
		userU.get().setNombre(user.getNombre());
		userU.get().setEmail(user.getEmail());
		userU.get().setClave(user.getClave());
		userU.get().setEstado(user.getEstado());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userU.get()));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		if(!service.findById(id).isPresent()) {
			ResponseEntity.notFound().build();
		}
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@GetMapping
	public List<User> readAll(){
		List<User> users = StreamSupport
				.stream(service.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return users;
	}
	
}
