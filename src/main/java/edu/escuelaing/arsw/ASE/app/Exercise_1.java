package edu.escuelaing.arsw.ASE.app;

import java.net.*;

public class Exercise_1 {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.ejemplo.com:8080/ruta/al/archivo?nombre=valor#seccion");
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