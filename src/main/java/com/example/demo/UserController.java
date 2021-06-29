package com.example.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;

	@GetMapping(path = "/users",produces = {"application/xml"} )
	public List<User> findAll() {
		return userDao.findAll();
	}

	@PostMapping("/users")
	public ResponseEntity save(@Valid @RequestBody User user) {
		User save = userDao.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save.getId()).toUri();
		// return ResponseEntity.status(HttpStatus.CREATED).build();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value  = "users/{id}", params = {"version=1"}/* , produces = {"application/xml","application/json"} */)
	public EntityModel<User> findOneV1(@PathVariable Integer id) {
		User user = userDao.findOne(id);
		
		// return all resources
		EntityModel<User> resources = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
		resources.add(linkTo.withRel("users"));
		
		return resources;
	}
	
	@GetMapping(value  = "users/{id}",params = {"version=2"}/* , produces = {"application/xml","application/json"} */)
	public EntityModel<User> findOneV2(@PathVariable Integer id) {
		User user = userDao.findOne(id);
		
		// return all resources
		EntityModel<User> resources = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
		resources.add(linkTo.withRel("users"));
		
		return resources;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		User deleteUser = userDao.deleteUser(id);
		if(deleteUser==null)
			throw new UserNotFoundException("id-" +id);
		//return deleteUser;
	}
}
