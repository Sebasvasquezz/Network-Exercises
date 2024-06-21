package edu.escuelaing.arsw.ASE.app.Exercise_2;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

/**
 * The Exercise_2 class allows the user to input a URL from the console,
 * retrieves the content from the URL, and saves it to a file named "resultado.html".
 */
public class Exercise_2 {
    
    /**
     * The main method is the entry point of the application. It prompts the user to enter a URL,
     * reads the content from the specified URL, and writes the content to a file.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una dirección URL:");
        String urlString = scanner.nextLine();

        try {
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter("resultado.html"));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            reader.close();
            writer.close();
            System.out.println("Los datos se han guardado en resultado.html");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
