package Lesson.WordFrequenciesProject;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();

        try {
            Scanner scanner = new Scanner(
                new File("Lesson/WordFrequenciesProject/likeit.txt")
            );

            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();

                int index = myWords.indexOf(word);

                if (index == -1) {
                    myWords.add(word);
                    myFreqs.add(1);
                } else {
                    int freq = myFreqs.get(index);
                    myFreqs.set(index, freq + 1);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public int findIndexOfMax() {
        int maxIndex = 0;

        for (int k = 1; k < myFreqs.size(); k++) {
            if (myFreqs.get(k) > myFreqs.get(maxIndex)) {
                maxIndex = k;
            }
        }

        return maxIndex;
    }

    public void tester() {
        findUnique();

        System.out.println("Number of unique words: " + myWords.size());

        //for (int k = 0; k < myWords.size(); k++) {
            //System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        //}

        //int maxIndex = findIndexOfMax();

        //System.out.println(
            //"The word that occurs most often and its count are: "
            //+ myWords.get(maxIndex) + " "
            //+ myFreqs.get(maxIndex)
        //);
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}