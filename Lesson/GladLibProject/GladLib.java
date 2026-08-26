package Lesson.GladLibProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GladLib {

    private ArrayList<String> nounList;
    private ArrayList<String> adjectiveList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> usedWords;

    private Random myRandom;

    public GladLib() {
        initializeFromSource();
        myRandom = new Random();
        usedWords = new ArrayList<String>();
    }

    private void initializeFromSource() {
        nounList = readIt("Lesson/GladLibProject/data/noun.txt");
        adjectiveList = readIt("Lesson/GladLibProject/data/adjective.txt");
        verbList = readIt("Lesson/GladLibProject/data/verb.txt");
        fruitList = readIt("Lesson/GladLibProject/data/fruit.txt");
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

        if (label.equals("noun")) {
            return randomFrom(nounList);

        } else if (label.equals("adjective")) {
            return randomFrom(adjectiveList);

        } else if (label.equals("verb")) {
            return randomFrom(verbList);

        } else if (label.equals("fruit")) {
            return randomFrom(fruitList);

        } else {
            return "**UNKNOWN**";
        }
    }

    private String randomFrom(ArrayList<String> list) {
        return list.get(myRandom.nextInt(list.size()));
    }

    private String processWord(String word) {

        if (word.startsWith("<") && word.endsWith(">")) {

            String label = word.substring(1, word.length() - 1);
            String replacement = getSubstitute(label);

            if (!replacement.equals("**UNKNOWN**")) {

                int attempts = 0;

                while (usedWords.contains(replacement) && attempts < 100) {
                    replacement = getSubstitute(label);
                    attempts++;
                }

                if (!usedWords.contains(replacement)) {
                    usedWords.add(replacement);
                }
            }

            return replacement;
        }

        return word;
    }

    public String makeStory() {

        usedWords.clear();

        StringBuilder story = new StringBuilder();

        try {
            Scanner scanner = new Scanner(
                new File("Lesson/GladLibProject/data/madtemplate2.txt")
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

    public static void main(String[] args) {

        GladLib gladLib = new GladLib();

        String story = gladLib.makeStory();

        System.out.println(story);

        System.out.println(
            "Total words replaced: " + gladLib.usedWords.size()
        );
    }
}