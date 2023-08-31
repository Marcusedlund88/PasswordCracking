package com.example.bruteforcedemo.Decode;

import com.example.bruteforcedemo.Controller.EndpointController;
import org.apache.tomcat.util.buf.Ascii;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BruteForce {

    private String encryptedPassword;
    private String guessedPassword;
    private boolean passFound = false;

    private EndpointController endpointController = new EndpointController();

    public BruteForce(String encryptedPassword, String guessedPassword) {
        this.encryptedPassword = encryptedPassword;
        this.guessedPassword = guessedPassword;
    }

    public void bruteForceDictionary(String username, String authUsername, String authPassword){
        passFound = false;
        endpointController.setPassFound(false);
        System.out.println("------------------Cracking Password please wait---------------------");

        String filePath = "C:\\Users\\Min Dator\\Desktop\\Marcus\\Security\\Exercise Two\\BruteForceDemo\\src\\main\\resources\\Passwords.txt";

        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int counter = 0;
            while((line = reader.readLine()) != null){
               passFound = endpointController.makeRequest(username, line, authUsername, authPassword);

               if(passFound){
                   return;
               }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //TODO: Create a method for reading passwords from file.
    }

    public void bruteForceSha(String username, String authUsername, String authPassword){

        //TODO: Create a method for cracking SHA encoded passwords.

    }
    public void bruteForceInt(String username, String authUsername, String authPassword) {
        passFound = false;
        endpointController.setPassFound(false);
        System.out.println("------------------Cracking Password please wait---------------------");

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

    public void bruteForceChar(int length, String username, String authUsername, String authPassword, int asciiStart, int asciiEnd){
        passFound = false;
        endpointController.setPassFound(false);
        System.out.println("------------------Cracking Password please wait---------------------");
        String password = "";

        char[] vector = new char[length];

        int alphabet = 26;

        for (int i = 0; i < vector.length; i++) {
            vector[i] = (char) (asciiStart);
        }
        generateLoops(password, length-1, vector, username, authUsername, authPassword, asciiStart, asciiEnd);
    }

    public String generateLoops(String password, int depth, char[] vector,
                                String username, String authUsername, String authPassword, int asciiStart, int asciiEnd){
        if (depth == -1) {

            String answer = new String(vector);

            return answer;
        }

        for (int i = asciiStart; i < asciiEnd; i++) {
            char character = (char) (i);

                passFound = endpointController.makeRequest(username, new String(vector), authUsername, authPassword);

            if(depth-1 >= -1) {
                vector[depth] = character;
                generateLoops(password, depth - 1, vector, username, authUsername, authPassword, asciiStart, asciiEnd);
            }
            if(passFound){
                break;
            }
        }
        return null;
    }
    public void bruteForceSpecial(String username, String authUsername, String authPassword){
        passFound = false;
        endpointController.setPassFound(false);
        System.out.println("------------------Cracking Password with Special Characters, please wait---------------------");

        int length = 4; // password length
        char[] vector = new char[length];

        String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-=_+";
        int alphabet = charset.length(); // size of your alphabet

        for (int i = 0; i < vector.length; i++) {
            vector[i] = charset.charAt(0);
        }
        generateLoopsSpecial("", length-1, alphabet, vector, charset, username, authUsername, authPassword);
    }

    public String generateLoopsSpecial(String password, int depth, int alphabet, char[] vector, String charset, String username, String authUsername, String authPassword) {
        if (depth == -1) {
            String answer = new String(vector);
            passFound = endpointController.makeRequest(username, answer, authUsername, authPassword);
            if(passFound){
                System.out.println("Password found: " + answer);
                return answer;
            }
            return null;
        }
        for (int i = 0; i < alphabet; i++) {
            char character = charset.charAt(i);
            vector[depth] = character;

            if(depth - 1 >= -1) {
                generateLoopsSpecial(password, depth - 1, alphabet, vector, charset, username, authUsername, authPassword);
            }

            if(passFound){
                break;
            }
        }

        return null;
    }

    public void bruteForceBcrypt(int passwordLength, String encodedPass){
        System.out.println("------------------Cracking Password with Special Characters, please wait---------------------");

        passFound = false;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

        int asciiStart = 33;
        int asciiEnd = 127;
        for (int i = 0; i < passwordLength; i++) {
            char[] vector = new char[i+1];
            generateLoopsBcrypt(vector.length-1, encodedPass, asciiStart, asciiEnd, vector, bCryptPasswordEncoder);
        }
    }

    public String generateLoopsBcrypt(int passwordLength, String encryptedPassword,
                                      int asciiStart, int asciiEnd, char[] vector, BCryptPasswordEncoder bCryptPasswordEncoder){
        if (passwordLength == -1) {

            String answer = new String(vector);

            return answer;
        }

        for (int i = asciiStart; i < asciiEnd+1; i++) {
            char character = (char) (i);
            vector[passwordLength] = character;
            //vector[passwordLength-1] = character;

           if(bCryptPasswordEncoder.matches(new String(vector), encryptedPassword)){
               passFound = true;
                System.out.println("You lucky bastard you did it!! Password is: " + new String(vector));
            }

            if(passwordLength-1 != -1) {
                generateLoopsBcrypt(passwordLength-1, encryptedPassword, asciiStart, asciiEnd, vector, bCryptPasswordEncoder);
            }
            if(passFound){
                System.out.println("");
                break;
            }
        }
        return null;
    }
}