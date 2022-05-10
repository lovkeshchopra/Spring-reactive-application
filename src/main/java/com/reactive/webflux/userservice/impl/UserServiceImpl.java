package com.reactive.webflux.userservice.impl;

import java.util.List;
import java.util.stream.IntStream;

import com.reactive.webflux.entity.User;
import com.reactive.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.reactive.webflux.dto.Address;
import com.reactive.webflux.dto.AlbumDTO;
import com.reactive.webflux.dto.ResponseDTO;
import com.reactive.webflux.dto.ResponseData;
import com.reactive.webflux.dto.UserDTO;
import com.reactive.webflux.dto.UserPosts;
import com.reactive.webflux.userservice.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private WebClient webClient;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Flux<UserDTO> getAllUsersWithWebClient() {

		Flux<UserDTO> response = webClient.get().uri("/users").retrieve().bodyToFlux(UserDTO.class);
		return response;

	}

	@Override
	public Flux<Address> getUsersAddress() {

		Flux<UserDTO> response = webClient.get().uri("/users").retrieve().bodyToFlux(UserDTO.class);
		return response.doOnNext(s -> System.out.println("City: " + s.getAddress().getCity()))
				.flatMap(data -> Flux.just(data.getAddress()));

	}

	@Override
	public Mono<List<String>> getPostWithUserIdForAllUsers() {

		Flux<UserDTO> response = webClient.get().uri("/users").retrieve().bodyToFlux(UserDTO.class);
		return response.flatMap(
				data -> webClient.get().uri("/users/" + data.getId() + "/posts").retrieve().bodyToFlux(UserPosts.class))
				.map(res -> res.getTitle().toUpperCase()).collectList();

	}

	@Override
	public Flux<Object> fluxWithMerge() {

		Flux<UserDTO> userResponse = webClient.get().uri("/users").retrieve().bodyToFlux(UserDTO.class);
		Flux<UserPosts> postsResponse = webClient.get().uri("/posts").retrieve().bodyToFlux(UserPosts.class);
		return Flux.merge(userResponse, postsResponse);
	}

	@Override
	public Flux<ResponseDTO> getAllUsersWithPosts() {

		Flux<UserDTO> userResponse = webClient.get().uri("/users").retrieve().bodyToFlux(UserDTO.class);
		return userResponse.flatMap(user -> {

			Mono<List<UserPosts>> userPosts = webClient.get().uri("/users/" + user.getId() + "/posts").retrieve()
					.bodyToFlux(UserPosts.class).collectList();
			return userPosts.map(posts -> new ResponseDTO(user, posts, null));
		}).log();
	}

	@Override
	public Flux<ResponseData> getAllUsersWithPostsAndAlbumsSequence() {

		Flux<UserDTO> userResponse = webClient.get().uri("/users").retrieve().bodyToFlux(UserDTO.class);
		return userResponse.flatMapSequential(user -> {

			Mono<List<UserPosts>> userPosts = webClient.get().uri("/users/" + user.getId() + "/posts").retrieve()
					.bodyToFlux(UserPosts.class).collectList();

			Mono<List<AlbumDTO>> userAlbums = webClient.get().uri("/users/" + user.getId() + "/albums").retrieve()
					.bodyToFlux(AlbumDTO.class).collectList();
			return userPosts.flatMap(
					usrPost -> userAlbums.map(album -> new ResponseData(new ResponseDTO(user, usrPost, album))));

		}).log();
	}

	@Override
	public Mono<ResponseDTO> getUsersWithAllPosts(int userId) {

		Mono<UserDTO> userResponse = webClient.get().uri("/users/" + userId).retrieve().bodyToMono(UserDTO.class);
		Mono<List<UserPosts>> postsResponse = webClient.get().uri("/users/" + userId + "/posts").retrieve()
				.bodyToFlux(UserPosts.class).collectList();

		return userResponse.zipWith(postsResponse, (user, posts) -> new ResponseDTO(user, posts, null));

	}

	@Override
	public Flux<ResponseDTO> fluxWithZip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<UserDTO> doOnOperations() {
		Flux<UserDTO> userResponse = webClient.get().uri("/users").retrieve().bodyToFlux(UserDTO.class);

		return userResponse.doOnSubscribe(subscription -> System.out.println(subscription.toString()));
	}

	@Override
	public Mono<User> save(User user) {
		  userRepository.save(user).subscribe();
		  return null;

	}

	private static void sleepExecution(int i ) throws InterruptedException {
		Thread.sleep(1000);
	}




	@Override
	public List<User> findAll() {
		long start = System.currentTimeMillis();
//		List<User> customers = userRepository.findAll();
		long end = System.currentTimeMillis();
		System.out.println("Total execution time : " + (end - start));
		return null;
	}


	@Override
	public Flux<User> getAllUserStream() {
		long start = System.currentTimeMillis();
		Flux<User> customers = userRepository.findAll();
		long end = System.currentTimeMillis();
		System.out.println("Total execution time : " + (end - start));
		return customers;
	}


}
