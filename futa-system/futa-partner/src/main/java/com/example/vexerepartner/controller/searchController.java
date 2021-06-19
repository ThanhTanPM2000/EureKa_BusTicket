package com.example.vexerepartner.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.vexerepartner.entity.Futa;
import com.example.vexerepartner.repository.FutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class searchController {

    @Autowired
    private FutaRepository futaRepository;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public List<Futa> getVexereTickets() {
        return futaRepository.findAll();
    }

    @RequestMapping(value = "/{from}/{to}", method = RequestMethod.GET)
    public List<Futa> getVexereTicketsFT(@PathVariable("from") String from, @PathVariable("to") String to) {
        try {
            TimeUnit.SECONDS.sleep(5);
            var listFuta = futaRepository.findAll();
            List<Futa> reponse = new ArrayList();
            for (Futa item : listFuta) {
                if (item.getFrom().equals(from) && item.getTo().equals(to)) {
                    reponse.add(item);
                }
            }
            return reponse;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return new ArrayList();
        }
    }
}
