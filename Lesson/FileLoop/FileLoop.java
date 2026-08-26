package Lesson.FileLoop;

import java.nio.file.Files; // Standard Java utility for reading files
import java.nio.file.Path;  // Standard Java class for file paths

public class FileLoop {

    public void runHello() throws Exception {
        // Path to the file inside the Lesson folder
        Path filePath = Path.of("Lesson/FileLoop/file.txt");

        // Loop through lines just like f.lines() in Duke's library
        for (String line : Files.readAllLines(filePath)) {
            System.out.println(line);
        }
    }

    public static void main(String[] args) throws Exception {
        // Create instance and run, matching the exact course structure
        FileLoop fl = new FileLoop();
        fl.runHello();
    }
}