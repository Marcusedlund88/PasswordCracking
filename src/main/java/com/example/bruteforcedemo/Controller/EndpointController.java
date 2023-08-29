package com.example.bruteforcedemo.Controller;

import org.springframework.http.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;


public class EndpointController {
    int x = 1;
    private boolean passFound = false;
    public boolean makeRequest(String username, String password, String authUsername,String authPassword) {
        String url = "http://localhost:8080/users/loginFixed";

        try {
            Thread.sleep(1);

            WebClient webClient = WebClient.builder()
                .baseUrl(url).defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + getBasicAuthHeader(authUsername, authPassword)).build();

            Mono<String> responseMono =
                webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam("username", username)
                                .queryParam("password", password).build())
                        .retrieve().bodyToMono(String.class);

        Disposable disposable = responseMono.subscribe(responseBody -> {
            if (!responseBody.equals("Invalid username or password")) {
                System.out.println("You lucky bastard you did it!! Password is: " + password);
                passFound = true;
            }
        });
        if(passFound) {
            disposable.dispose();
        }
    }

             catch (InterruptedException e){
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("Error ");
        }
        return passFound;
    }
    private static String getBasicAuthHeader(String username, String password) {
        String credentials = username + ":" + password;
        return java.util.Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}
