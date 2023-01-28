package com.example.doenerwo.service;

import com.example.doenerwo.repository.DoenerstandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ReadKoordinatesFromGivenAddress {
    //HttpClient client = HttpClient.newHttpClient();
    //HttpRequest request = HttpRequest.newBuilder()
            //.uri(URI.create("DUMMY_URL"))
            //.build();

    //public ReadKoordinatesFromGivenAddress() throws IOException, InterruptedException {
        //HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

    //}

    @Autowired
    DoenerstandRepository repo;

    public static String getUrlResponse() {
        WebClient webClient = WebClient.create("https://www.google.at/maps/place/Kohlenhofgasse");

        Mono<String> body = webClient.get().retrieve().bodyToMono(String.class);

        String s = body.block();
        String ss = s;



        return s;
    }

}
