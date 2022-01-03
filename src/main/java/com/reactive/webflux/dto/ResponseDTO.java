package com.reactive.webflux.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

	private UserDTO user;

	private List<UserPosts> posts;

	private List<AlbumDTO> album;

}
