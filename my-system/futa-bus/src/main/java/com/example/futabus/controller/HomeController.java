package com.example.futabus.controller;

import com.example.futabus.entity.Futa;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @RequestMapping("/{from}/{to}")
    public List getFuTaTickets(@PathVariable("from") final String from, @PathVariable("to") final String to) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:1106/futa/" + from + "/" +to).openConnection();
        connection.setRequestMethod("GET");
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = inputReader.readLine()) != null) {
            content.append(inputLine);
        }
        inputReader.close();
        Gson gson = new Gson();
        return gson.fromJson(content.toString(), List.class);
    }
}
