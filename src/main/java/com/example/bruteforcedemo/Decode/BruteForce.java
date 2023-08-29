package com.example.bruteforcedemo.Decode;

import com.example.bruteforcedemo.Controller.EndpointController;
import org.apache.tomcat.util.buf.Ascii;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

public class BruteForce {

    private String encryptedPassword;
    private String guessedPassword;
    private boolean passFound = false;

    private EndpointController endpointController = new EndpointController();

    public BruteForce(String encryptedPassword, String guessedPassword) {
        this.encryptedPassword = encryptedPassword;
        this.guessedPassword = guessedPassword;
    }

    public void bruteForceInt(String username, String authUsername, String authPassword) {

        System.out.println("------------------Cracking Password please way---------------------");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        /*
        String password = bCryptPasswordEncoder.encode(guessedPassword);
        System.out.println(password);*/
        EndpointController endpointController = new EndpointController();
        try {
            for (int i = 0; i < 999999999; i++) {

                String guess = Integer.toString(i);

                passFound = endpointController.makeRequest(username, guess, authUsername, authPassword);

                if(passFound){
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bruteForceChar(int length, String username, String authUsername, String authPassword){

        System.out.println("------------------Cracking Password please way---------------------");
        String password = "";

        char[] vector = new char[length];

        int alphabet = 26;

        for (int i = 0; i < vector.length; i++) {
            vector[i] = 'a';
        }
        generateLoops(password, length-1, alphabet, vector, username, authUsername, authPassword);
    }

    public String generateLoops(String password, int depth, int alphabet, char[] vector, String username, String authUsername, String authPassword){
        if (depth == -1) {

            String answer = new String(vector);

            return answer;
        }

        for (int i = 0; i < alphabet; i++) {
            char character = (char) (i + 97);
                passFound = endpointController.makeRequest(username, new String(vector), authUsername, authPassword);

            if(depth-1 >= -1) {
                vector[depth] = character;
                generateLoops(password, depth - 1, alphabet, vector, username, authUsername, authPassword);
            }
            if(passFound){
                break;
            }
        }
        return null;
    }
}