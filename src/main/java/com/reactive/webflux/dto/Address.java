package com.reactive.webflux.dto;

import lombok.Data;

@Data
public class Address {

	private String street;

	private String suit;

	private String city;

	private String zipCode;

	private Geo geo;
}
