package com.example.bruteforcedemo;

import com.example.bruteforcedemo.Controller.EndpointController;
import com.example.bruteforcedemo.Decode.BruteForce;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BruteForceDemoApplication {

    public static void main(String[] args){
        SpringApplication.run(BruteForceDemoApplication.class, args);

        String encryptedPassword = "$2a$10$3ZGu.69QI1tn.VsI3s3qTe4QvXhHi5D7jzQ5WlGCZ/g9jAe.CapkS";
        String guessedPassword = "anna";

        String authUsername = "user";
        String authPassword = "28b3d01f-d1fc-4704-a7d2-85c6a6f0c6a6";

        BruteForce bruteForce = new BruteForce(encryptedPassword, guessedPassword);

        bruteForce.bruteForceChar(4,"marcus",authUsername,authPassword);
        bruteForce.bruteForceInt("hurtigh", authUsername,authPassword);
        bruteForce.bruteForceDictionary("admin",authUsername, authPassword);


        //EndpointController endpointController = new EndpointController();


       // bruteForce.bruteForceInt(6, "$2a$10$.xILN2u/b9wJWKMFH/EB4eLlFJKvcYIAadXTMnbVUFJgHpLX4d2hi", restTemplate,"hurtigh");
    }
}
