package com.reactive.webflux.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/getAllUsers")
	public Flux<UserDTO> getAllUsers() {

		return userService.getAllUsers().log();

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
