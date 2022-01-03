package com.reactive.webflux.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO {

	private int id;

	private String name;

	private String username;

	private String email;

	private Address address;

	private String phone;

	private String website;

	private Company company;
}
