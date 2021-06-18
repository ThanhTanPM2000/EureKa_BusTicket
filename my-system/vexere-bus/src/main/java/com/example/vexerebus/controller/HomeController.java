package com.example.vexerebus.controller;

import com.example.vexerebus.entity.VeXeRe;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @RequestMapping("/{from}/{to}")
    public List getVeXeReTickets(@PathVariable("from") final String from, @PathVariable final String to) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8764/vexere/" + from + "/" + to).openConnection();
        connection.setRequestMethod("GET");
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = inputReader.readLine()) != null) {
            content.append(inputLine);
        }
        inputReader.close();
        Gson gson = new Gson();
        System.out.println(gson.fromJson(content.toString(), VeXeRe[].class));
        return gson.fromJson(content.toString(), List.class);
    }

    @GetMapping(value = "/{from}/{to}/{day}/{month}/{year}")
    public List<VeXeRe> getData(@PathVariable("from") final String from, @PathVariable("to") final String to, @PathVariable("day") final String day, @PathVariable("month") final String month, @PathVariable("year") final String year) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8764/vexere/" + from + "/" + to + "/" + day + "/" + month + "/" + year).openConnection();
        connection.setRequestMethod("GET");
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = inputReader.readLine()) != null) {
            content.append(inputLine);
        }
        inputReader.close();
        Gson gson = new Gson();
        System.out.println(gson.fromJson(content.toString(), VeXeRe[].class));
        return gson.fromJson(content.toString(), List.class);
    }
}
