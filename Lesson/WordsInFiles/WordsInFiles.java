package Lesson.WordsInFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> wordsMap;

    public WordsInFiles() {
        wordsMap = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {
        try {
            Scanner scanner = new Scanner(f);

            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();

                if (!wordsMap.containsKey(word)) {
                    ArrayList<String> fileList = new ArrayList<String>();
                    fileList.add(f.getName());
                    wordsMap.put(word, fileList);
                } else {
                    ArrayList<String> fileList = wordsMap.get(word);

                    if (!fileList.contains(f.getName())) {
                        fileList.add(f.getName());
                    }
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + f.getName());
        }
    }

    public void buildWordFileMap() {
        wordsMap.clear();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the folder path: ");
        String folderPath = scanner.nextLine();

        File folder = new File(folderPath);

        File[] files = folder.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.isFile()) {
                    addWordsFromFile(f);
                }
            }
        } else {
            System.out.println("Folder not found.");
        }

        scanner.close();
    }

    public int maxNumber() {
        int max = 0;

        for (String word : wordsMap.keySet()) {
            int number = wordsMap.get(word).size();

            if (number > max) {
                max = number;
            }
        }

        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();

        for (String word : wordsMap.keySet()) {
            int fileCount = wordsMap.get(word).size();

            if (fileCount == number) {
                words.add(word);
            }
        }

        return words;
    }

    public void printFilesIn(String word) {
        if (wordsMap.containsKey(word)) {
            ArrayList<String> files = wordsMap.get(word);

            for (String file : files) {
                System.out.println(file);
            }
        }
    }

    public void tester() {
        buildWordFileMap();

        int max = maxNumber();

        System.out.println("Maximum number of files: " + max);

        ArrayList<String> words = wordsInNumFiles(max);

        System.out.println("Words that appear in " + max + " files:");

        for (String word : words) {
            System.out.println();
            System.out.println(word);
            printFilesIn(word);
        }
        System.out.println("Q4: " + wordsInNumFiles(5).size());
        System.out.println("Q5: " + wordsInNumFiles(4).size());

        System.out.println("Files containing sad:");
        printFilesIn("sad");

        System.out.println("Files containing red:");
        printFilesIn("red");
    }

    public static void main(String[] args) {
        WordsInFiles wif = new WordsInFiles();
        wif.tester();
    }
}