package Lesson.GladLibMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class GladLibMap {

    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCategories;

    private Random myRandom;

    public GladLibMap() {
        myMap = new HashMap<String, ArrayList<String>>();
        usedWords = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
        myRandom = new Random();

        initializeFromSource();
    }

    private void initializeFromSource() {

        String[] categories = {
            "adjective",
            "animal",
            "color",
            "country",
            "fruit",
            "name",
            "noun",
            "timeframe",
            "verb"
        };

        for (String category : categories) {

            String fileName;

            if (category.equals("time")) {
                fileName = "Lesson/GladLibProject/data/timeframe.txt";
            } else {
                fileName = "Lesson/GladLibProject/data/"
                        + category + ".txt";
            }

            ArrayList<String> list = readIt(fileName);

            myMap.put(category, list);
        }
    }

    private ArrayList<String> readIt(String source) {

        ArrayList<String> list = new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(new File(source));

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine().trim();

                if (!line.isEmpty()) {
                    list.add(line);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + source);
        }

        return list;
    }

    private String getSubstitute(String label) {

        if (!myMap.containsKey(label)) {
            return "**UNKNOWN**";
        }

        if (!usedCategories.contains(label)) {
            usedCategories.add(label);
        }

        return randomFrom(myMap.get(label));
    }

    private String randomFrom(ArrayList<String> list) {

        return list.get(myRandom.nextInt(list.size()));
    }

    private String processWord(String word) {

        int start = word.indexOf("<");
        int end = word.indexOf(">");

        if (start != -1 && end != -1 && start < end) {

            String label = word.substring(start + 1, end);

            String replacement = getSubstitute(label);

            if (!replacement.equals("**UNKNOWN**")) {

                int attempts = 0;

                while (usedWords.contains(replacement)
                        && attempts < 100) {

                    replacement = getSubstitute(label);
                    attempts++;
                }

                if (!usedWords.contains(replacement)) {
                    usedWords.add(replacement);
                }

                String before = word.substring(0, start);
                String after = word.substring(end + 1);

                return before + replacement + after;
            }

            return replacement;
        }

        return word;
    }

    public String makeStory() {

        usedWords.clear();
        usedCategories.clear();

        StringBuilder story = new StringBuilder();

        try {
            Scanner scanner = new Scanner(
                new File(
                    "Lesson/GladLibProject/data/madtemplate2.txt"
                )
            );

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                Scanner lineScanner = new Scanner(line);

                while (lineScanner.hasNext()) {

                    String word = lineScanner.next();

                    story.append(processWord(word));
                    story.append(" ");
                }

                lineScanner.close();

                story.append("\n");
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Template file not found.");
        }

        return story.toString().trim();
    }

    public int totalWordsInMap() {

        int total = 0;

        for (String category : myMap.keySet()) {
            total += myMap.get(category).size();
        }

        return total;
    }

    public int totalWordsConsidered() {

        int total = 0;

        for (String category : usedCategories) {
            total += myMap.get(category).size();
        }

        return total;
    }

    public static void main(String[] args) {

        GladLibMap gladLib = new GladLibMap();

        String story = gladLib.makeStory();

        System.out.println(story);

        System.out.println();

        System.out.println(
            "Total words replaced: "
            + gladLib.usedWords.size()
        );

        System.out.println();

        System.out.println(
            "Total words possible to pick from: "
            + gladLib.totalWordsInMap()
        );

        System.out.println();

        System.out.println(
            "Total words considered: "
            + gladLib.totalWordsConsidered()
        );
    }
}