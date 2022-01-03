package com.reactive.webflux.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserPosts {

	private int userId;

	private int id;

	private String title;

	private String body;

}
