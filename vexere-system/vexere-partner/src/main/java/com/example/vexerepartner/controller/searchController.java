package com.example.vexerepartner.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.vexerepartner.entity.VeXeRe;

import com.example.vexerepartner.repository.VexereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class searchController {

	@Autowired
	private VexereRepository vexereRepository;

	@Autowired
	private Environment env;

	@RequestMapping(value = "/tickets",method = RequestMethod.GET)
	public List<VeXeRe> getVexereTickets() {
		return vexereRepository.findAll();
	}

	@RequestMapping(value = "/{from}/{to}",method = RequestMethod.GET)
	public List<VeXeRe> getVexereTicketsFT(@PathVariable("from") String from , @PathVariable("to") String to) {
		var listVexere = vexereRepository.findAll();
		List<VeXeRe> reponse = new ArrayList();
		for(VeXeRe item: listVexere) {
			if(item.getFrom().equals(from) && item.getTo().equals(to)) {
				reponse.add(item);
			}
		}
		return reponse;
	}
}
