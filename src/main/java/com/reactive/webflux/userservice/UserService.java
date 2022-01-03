package com.reactive.webflux.userservice;

import java.util.List;

import com.reactive.webflux.dto.Address;
import com.reactive.webflux.dto.ResponseDTO;
import com.reactive.webflux.dto.ResponseData;
import com.reactive.webflux.dto.UserDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

	public Flux<UserDTO> getAllUsers();

	public Flux<Address> getUsersAddress();

	public Mono<List<String>> getPostWithUserIdForAllUsers();

	public Flux<ResponseDTO> fluxWithZip();

	public Flux<Object> fluxWithMerge();

	public Mono<ResponseDTO> getUsersWithAllPosts(int userId);

	public Flux<ResponseDTO> getAllUsersWithPosts();

	public Flux<ResponseData> getAllUsersWithPostsAndAlbumsSequence();

	public Flux<UserDTO> doOnOperations();

}
