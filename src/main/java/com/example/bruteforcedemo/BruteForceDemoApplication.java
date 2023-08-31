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
        String authPassword = "b73b3ccf-6d96-4fa5-9518-d8c0480a9701";

        BruteForce bruteForce = new BruteForce(encryptedPassword, guessedPassword);

        //bruteForce.bruteForceChar(4,"marcus",authUsername,authPassword, 33, 122);
        //bruteForce.bruteForceInt("hurtigh", authUsername,authPassword);
        //bruteForce.bruteForceDictionary("admin",authUsername, authPassword);
        //bruteForce.bruteForceSpecial("admin", authUsername, authPassword);
        //bruteForce.bruteForceBcrypt(4, encryptedPassword);

        //EndpointController endpointController = new EndpointController();


       // bruteForce.bruteForceInt(6, "$2a$10$.xILN2u/b9wJWKMFH/EB4eLlFJKvcYIAadXTMnbVUFJgHpLX4d2hi", restTemplate,"hurtigh");
    }
}

/*    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter encrypted password:");
        String encryptedPassword = scanner.nextLine();

        System.out.println("Enter guessed password:");
        String guessedPassword = scanner.nextLine();

        System.out.println("Enter auth username:");
        String authUsername = scanner.nextLine();

        System.out.println("Enter auth password:");
        String authPassword = scanner.nextLine();

        BruteForce bruteForce = new BruteForce(encryptedPassword, guessedPassword);

        System.out.println("Select brute force method:");
        System.out.println("1: Brute force characters");
        System.out.println("2: Brute force integers");
        System.out.println("3: Brute force sha ");

        int selection = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (selection) {
            case 1:
                System.out.println("Executing brute force on characters...");
                bruteForce.bruteForceChar(4, "marcus", authUsername, authPassword);
                break;
            case 2:
                System.out.println("Executing brute force on integers...");
                bruteForce.bruteForceInt("hurtigh", authUsername, authPassword);
                break;
            default:
                System.out.println("Invalid selection");
                break;
        }

        scanner.close();
    }
}*/