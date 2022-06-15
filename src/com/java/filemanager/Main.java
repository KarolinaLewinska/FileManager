package com.java.filemanager;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager();

        do {
            System.out.print("Enter a file path to move: ");
            String userInput = scanner.nextLine();
            if (userInput.equals("exit")) {
                System.exit(0);
            }
            if (userInput.isBlank() || userInput.isEmpty()) {
                continue;
            }
            try {
                fileManager.moveFile(userInput);
            } catch (IOException exc) {
                System.out.println("Could not find a file. Try again");
            }
        } while (true);
    }
}