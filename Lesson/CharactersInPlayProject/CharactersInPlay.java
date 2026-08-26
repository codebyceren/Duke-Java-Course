package Lesson.CharactersInPlayProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }

    public void update(String person) {
        int index = names.indexOf(person);

        if (index == -1) {
            names.add(person);
            counts.add(1);
        } else {
            int count = counts.get(index);
            counts.set(index, count + 1);
        }
    }

    public void findAllCharacters() {
    names.clear();
    counts.clear();

    try {
        Scanner scanner = new Scanner(
            new File("Lesson/CharactersInPlayProject/macbethSmall.txt")
        );

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            int periodIndex = line.indexOf(".");

            if (periodIndex != -1) {
                String person = line.substring(0, periodIndex).trim();
                update(person);
            }
        }

        scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public void tester() {
        findAllCharacters();

        System.out.println("Characters with 2 to 3 speaking parts:");

        charactersWithNumParts(2, 3);
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int k = 0; k < counts.size(); k++) {
            if (counts.get(k) >= num1 && counts.get(k) <= num2) {
                System.out.println(names.get(k) + "\t" + counts.get(k));
            }
        }
    }

    public static void main(String[] args) {
        CharactersInPlay cp = new CharactersInPlay();
        cp.tester();
    }
}
