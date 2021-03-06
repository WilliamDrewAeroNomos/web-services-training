/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * 
 * @author <a href=mailto:support@aeronomos.org>aeronomos</a>
 *
 *         $Id: $
 * 
 */
@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	/**
	 * Gets all users.
	 * 
	 * @return
	 */
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	/**
	 * Gets a single user specified by user ID.
	 * 
	 * @param id
	 *          Unique identifier for user.
	 * @return {@link User} matching the specified ID param.
	 */
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id = " + id);
		}
		Resource<User> resource = new Resource<User>(user);

		ControllerLinkBuilder linkTo =
				linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {

		User user = service.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("id = " + id);
		}
	}

	/**
	 * Create a user and return the URI.
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User newUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
