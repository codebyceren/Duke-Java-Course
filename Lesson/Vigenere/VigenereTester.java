package Lesson.Vigenere;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class VigenereTester {

    public static void main(String[] args) {

        VigenereBreaker breaker = new VigenereBreaker();

        /*
         * Test 1: secretmessage2.txt
         * Known key length: 57
         */

        File file = new File(
                "Lesson/Vigenere/data/secretmessage3.txt"
        );

        File dictionaryFile = new File(
                "Lesson/Vigenere/dictionaries/English"
        );

        try {

            Scanner scanner = new Scanner(file);

            StringBuilder encrypted = new StringBuilder();

            while (scanner.hasNextLine()) {

                encrypted.append(scanner.nextLine());

                if (scanner.hasNextLine()) {

                    encrypted.append("\n");
                }
            }

            scanner.close();

            HashSet<String> dictionary =
                    breaker.readDictionary(dictionaryFile);

            int keyLength = 57;

            int[] key =
                    breaker.tryKeyLength(
                            encrypted.toString(),
                            keyLength,
                            'e'
                    );

            VigenereCipher cipher =
                    new VigenereCipher(key);

            String decrypted =
                    cipher.decrypt(encrypted.toString());

            int count =
                    breaker.countWords(
                            decrypted,
                            dictionary
                    );

            System.out.println("=== Secret Message 2 ===");
            System.out.println("Key length: " + keyLength);
            System.out.println("Valid words: " + count);
            System.out.println("First line:");
            System.out.println(
                    decrypted.split("\\R", 2)[0]
            );

            /*
             * Test key length 38
             */

            int[] key38 =
                    breaker.tryKeyLength(
                            encrypted.toString(),
                            38,
                            'e'
                    );

            VigenereCipher cipher38 =
                    new VigenereCipher(key38);

            String decrypted38 =
                    cipher38.decrypt(encrypted.toString());

            int count38 =
                    breaker.countWords(
                            decrypted38,
                            dictionary
                    );

            System.out.println(
                    "Key length 38 valid words: " + count38
            );

        } catch (FileNotFoundException e) {

            System.out.println(
                    "File not found: " + e.getMessage()
            );
        }

        /*
         * Test 2: Multiple Languages
         */

        System.out.println();
        System.out.println("=== Multiple Languages ===");

        breaker.breakVigenere();
    }
}