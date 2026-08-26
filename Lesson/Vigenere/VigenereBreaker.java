package Lesson.Vigenere;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;

public class VigenereBreaker {

    public String sliceString(String message, int whichSlice, int totalSlices) {

        StringBuilder slice = new StringBuilder();

        for (int i = whichSlice; i < message.length(); i += totalSlices) {

            slice.append(message.charAt(i));

        }

        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {

        int[] key = new int[klength];

        for (int i = 0; i < klength; i++) {

            String slice = sliceString(encrypted, i, klength);

            CaesarCracker cracker = new CaesarCracker(mostCommon);

            key[i] = cracker.getKey(slice);
        }

        return key;
    }

    public HashSet<String> readDictionary(File file) {

        HashSet<String> dictionary = new HashSet<String>();

        try {

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String word = scanner.nextLine().toLowerCase();

                dictionary.add(word);
            }

            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println(
                    "Dictionary file not found: " + file.getPath()
            );
        }

        return dictionary;
    }

    public int countWords(String message, HashSet<String> dictionary) {

        String[] words = message.split("\\W+");

        int count = 0;

        for (String word : words) {

            if (dictionary.contains(word.toLowerCase())) {

                count++;
            }
        }

        return count;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {

        int[] counts = new int[26];

        for (String word : dictionary) {

            for (int i = 0; i < word.length(); i++) {

                char ch = word.charAt(i);

                if (ch >= 'a' && ch <= 'z') {

                    counts[ch - 'a']++;
                }
            }
        }

        int maxIndex = 0;

        for (int i = 1; i < counts.length; i++) {

            if (counts[i] > counts[maxIndex]) {

                maxIndex = i;
            }
        }

        return (char) ('a' + maxIndex);
    }

    public String breakForLanguage(String encrypted,
                                   HashSet<String> dictionary) {

        int maxCount = 0;

        String bestDecryption = "";

        char mostCommon = mostCommonCharIn(dictionary);

        for (int keyLength = 1; keyLength <= 100; keyLength++) {

            int[] key = tryKeyLength(
                    encrypted,
                    keyLength,
                    mostCommon
            );

            VigenereCipher cipher = new VigenereCipher(key);

            String decrypted = cipher.decrypt(encrypted);

            int count = countWords(decrypted, dictionary);

            if (count > maxCount) {

                maxCount = count;

                bestDecryption = decrypted;
            }
        }

        return bestDecryption;
    }

    public void breakForAllLangs(
            String encrypted,
            HashMap<String, HashSet<String>> languages) {

        int maxCount = 0;

        String bestLanguage = "";
        String bestDecryption = "";

        for (String language : languages.keySet()) {

            HashSet<String> dictionary = languages.get(language);

            String decrypted =
                    breakForLanguage(encrypted, dictionary);

            int count =
                    countWords(decrypted, dictionary);

            if (count > maxCount) {

                maxCount = count;

                bestLanguage = language;

                bestDecryption = decrypted;
            }
        }

        System.out.println("Language: " + bestLanguage);
        System.out.println("Decrypted message:");
        System.out.println(bestDecryption);
    }

    public void breakVigenere() {

        File encryptedFile =
            new File("Lesson/Vigenere/data/secretmessage3.txt");

        try {

            Scanner scanner = new Scanner(encryptedFile);

            StringBuilder encrypted = new StringBuilder();

            while (scanner.hasNextLine()) {

                encrypted.append(scanner.nextLine());

                if (scanner.hasNextLine()) {

                    encrypted.append("\n");
                }
            }

            scanner.close();

            HashMap<String, HashSet<String>> languages =
                    new HashMap<String, HashSet<String>>();

            String[] languageNames = {
                    "Danish",
                    "Dutch",
                    "English",
                    "French",
                    "German",
                    "Italian",
                    "Portuguese",
                    "Spanish"
            };

            for (String language : languageNames) {

                File dictionaryFile =
                        new File(
                                "Lesson/Vigenere/dictionaries/"
                                        + language
                        );

                System.out.println(
                        "Reading " + language + " dictionary..."
                );

                HashSet<String> dictionary =
                        readDictionary(dictionaryFile);

                languages.put(language, dictionary);
            }

            breakForAllLangs(
                    encrypted.toString(),
                    languages
            );

        } catch (FileNotFoundException e) {

            System.out.println(
                    "Encrypted file not found: "
                            + encryptedFile.getPath()
            );
        }
    }
}