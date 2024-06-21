package edu.escuelaing.arsw.ASE.app.Exercise_1;

import java.net.*;

/**
 * The Exercise_1 class demonstrates how to parse and extract components from a URL string.
 * It uses the java.net.URL class to parse a URL and then prints various components of the URL.
 */
public class Exercise_1 {

     /**
     * The main method is the entry point of the application. It creates a URL object from a string
     * and prints its various components such as protocol, authority, host, port, path, query, file, and reference.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        try {
            URL url = new URL("https://github.com/Sebasvasquezz/Network-Exercises");
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Authority: " + url.getAuthority());
            System.out.println("Host: " + url.getHost());
            System.out.println("Port: " + url.getPort());
            System.out.println("Path: " + url.getPath());
            System.out.println("Query: " + url.getQuery());
            System.out.println("File: " + url.getFile());
            System.out.println("Ref: " + url.getRef());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}