package Lesson.CommonWordsProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommonWords {

    // Count common words from a file
    public String[] getCommon() throws FileNotFoundException {
        File file = new File(
            "Lesson/CommonWordsProject/data/common.txt"
        );

        Scanner scanner = new Scanner(file);

        String[] common = new String[20];
        int index = 0;

        while (scanner.hasNext()) {
            common[index] = scanner.next();
            index++;
        }

        scanner.close();

        return common;
    }

    public int indexOf(String[] list, String word) {
        for (int k = 0; k < list.length; k++) {
            if (list[k] != null && list[k].equals(word)) {
                return k;
            }
        }

        return -1;
    }

    public void countWords(File file, String[] common, int[] counts)
            throws FileNotFoundException {

        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();

            word = word.replaceAll("[^a-z]", "");

            int index = indexOf(common, word);

            if (index != -1) {
                counts[index]++;
            }
        }

        scanner.close();
    }

    public void countShakespeare() throws FileNotFoundException {

        String[] plays = {
            "caesar.txt",
            "errors.txt",
            "hamlet.txt",
            "likeit.txt",
            "macbeth.txt",
            "romeo.txt"
        };

        String[] common = getCommon();
        int[] counts = new int[common.length];

        for (int k = 0; k < plays.length; k++) {

            File file = new File(
                "Lesson/CommonWordsProject/data/" + plays[k]
            );

            countWords(file, common, counts);
        }

        for (int k = 0; k < common.length; k++) {
            System.out.println(
                common[k] + "\t" + counts[k]
            );
        }

        int index = findIndexOfMax(counts);

        System.out.println(
            "The most common word is \"" + common[index]
            + "\" with count " + counts[index]
        );
    }

    // Find the index of the largest value
    public int findIndexOfMax(int[] values) {

        int maxIndex = 0;

        for (int k = 0; k < values.length; k++) {

            if (values[k] > values[maxIndex]) {
                maxIndex = k;
            }
        }

        return maxIndex;
    }

    // Count words according to their length
    public void countWordLengths(File file, int[] counts)
            throws FileNotFoundException {

        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {

            String word = scanner.next();

            int start = 0;
            int end = word.length();

            while (start < end &&
                   !Character.isLetter(word.charAt(start))) {
                start++;
            }

            while (end > start &&
                   !Character.isLetter(word.charAt(end - 1))) {
                end--;
            }

            int length = end - start;

            if (length >= counts.length) {
                counts[counts.length - 1]++;
            } else {
                counts[length]++;
            }
        }

        scanner.close();
    }

    // Test the word length counting method
    public void testCountWordLengths(String fileName)
            throws FileNotFoundException {

        File file = new File(
            "Lesson/CommonWordsProject/data/" + fileName
        );

        int[] counts = new int[31];

        countWordLengths(file, counts);

        for (int k = 0; k < counts.length; k++) {
            System.out.println(
                k + "\t" + counts[k]
            );
        }

        int index = findIndexOfMax(counts);

        System.out.println(
            "The most common word length is " + index
        );
    }

    public static void main(String[] args)
            throws FileNotFoundException {

        CommonWords cw = new CommonWords();

        cw.testCountWordLengths("manywords.txt");
    }
}