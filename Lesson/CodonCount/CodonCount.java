package Lesson.CodonCount;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CodonCount {

    private HashMap<String, Integer> codonMap;

    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        codonMap.clear();

        dna = dna.toUpperCase().trim();

        for (int i = start; i <= dna.length() - 3; i += 3) {
            String codon = dna.substring(i, i + 3);

            if (codonMap.containsKey(codon)) {
                codonMap.put(codon, codonMap.get(codon) + 1);
            } else {
                codonMap.put(codon, 1);
            }
        }
    }

    public String getMostCommonCodon() {
        String mostCommonCodon = "";
        int largestCount = 0;

        for (String codon : codonMap.keySet()) {
            int count = codonMap.get(codon);

            if (count > largestCount) {
                largestCount = count;
                mostCommonCodon = codon;
            }
        }

        return mostCommonCodon;
    }

    public void printCodonCounts(int start, int end) {
        for (String codon : codonMap.keySet()) {
            int count = codonMap.get(codon);

            if (count >= start && count <= end) {
                System.out.println(codon + "\t" + count);
            }
        }
    }

    public void tester() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the DNA file path: ");
        String fileName = scanner.nextLine();

        try {
            Scanner fileScanner = new Scanner(new File(fileName));

            String dna = "";

            while (fileScanner.hasNextLine()) {
                dna += fileScanner.nextLine();
            }

            fileScanner.close();

            for (int start = 0; start < 3; start++) {

                buildCodonMap(start, dna);

                System.out.println();
                System.out.println("Reading frame starting with " + start
                        + " results in " + codonMap.size()
                        + " unique codons");

                String mostCommonCodon = getMostCommonCodon();
                int count = codonMap.get(mostCommonCodon);

                System.out.println("and most common codon is "
                        + mostCommonCodon
                        + " with count " + count);

                System.out.println();
                System.out.println(
                        "Counts of codons between 1 and 5 inclusive are:");

                printCodonCounts(1, 5);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        scanner.close();
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.tester();
    }
}