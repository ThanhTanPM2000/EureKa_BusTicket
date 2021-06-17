package com.example.vexerepartner.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.example.vexerepartner.entity.VeXeRe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/")
public class searchController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/")
	public List<Object> home() {
		List<Object> tickets = Arrays.asList(
				new VeXeRe(1, "Ve Xe Re", "Ha Noi", "Ho Chi Minh", "" + LocalDateTime.now(), 255000),
				new VeXeRe(2, "Ve Xe Re", "Da Nang", "Ho Chi Minh", "" + LocalDateTime.now(), 200000),
				new VeXeRe(3, "Ve Xe Re", "Can Tho", "Ho Chi Minh", "" + LocalDateTime.now(), 200000));

		return tickets;
	}

}
