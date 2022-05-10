package com.reactive.webflux.restcontroller;

import java.util.List;

import com.reactive.webflux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.reactive.webflux.dto.Address;
import com.reactive.webflux.dto.ResponseDTO;
import com.reactive.webflux.dto.ResponseData;
import com.reactive.webflux.dto.UserDTO;
import com.reactive.webflux.userservice.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<User> save(@RequestBody User user) {
		return userService.save(user);
	}

	@GetMapping("/getAllUsers")
	public Flux<UserDTO> getAllUsersWithWebClient() {
		return userService.getAllUsersWithWebClient();

	}

	@GetMapping(value = "/allUsers")
	public List<User> getAllUser() throws InterruptedException {
		return userService.findAll();
	}

	@GetMapping(value = "/allUsers/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<User> getAllUserStream() {
		return userService.getAllUserStream();
	}

	@GetMapping("/getAddress")
	public Flux<Address> getAddress() {

		return userService.getUsersAddress().log();

	}

	@GetMapping("/getPostWithIds")
	public Mono<List<String>> getPostWithIds() {

		return userService.getPostWithUserIdForAllUsers().log();

	}

	@GetMapping("/fluxWithMerge")
	public Flux<Object> fluxWithMerge() {

		return userService.fluxWithMerge().log();

	}

	@GetMapping("/userWithPosts/{userId}")
	public Mono<ResponseDTO> userWithPosts(@PathVariable int userId) {

		return userService.getUsersWithAllPosts(userId).log();

	}

	@GetMapping("/allUsersWithPosts")
	public Flux<ResponseDTO> getAllUsersWithPosts() {

		return userService.getAllUsersWithPosts().log();

	}

	@GetMapping("/allUsersWithPostsWithAlbums")
	public Flux<ResponseData> getAllUsersWithPostsAndAlbumsSequence() {

		return userService.getAllUsersWithPostsAndAlbumsSequence().log();

	}

	@GetMapping("/doOnOperations")
	public Flux<UserDTO> doOnOperations() {

		return userService.doOnOperations().log();

	}

}
