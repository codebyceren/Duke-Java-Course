package Lesson;
import java.util.ArrayList;

public class GeneFinder {

    public ArrayList<String> getAllGenes(String dna) {
        // Using standard ArrayList instead of StorageResource
        ArrayList<String> geneList = new ArrayList<>();
        int startIndex = 0;

        while (true) {
            int startCodon = dna.indexOf("ATG", startIndex);
            if (startCodon == -1) {
                break;
            }
            int taaIndex = findStopCodon(dna, startCodon, "TAA");
            int tagIndex = findStopCodon(dna, startCodon, "TAG");
            int tgaIndex = findStopCodon(dna, startCodon, "TGA");

            int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
            if (minIndex == dna.length()) {
                startIndex = startCodon + 3;
            } else {
                String gene = dna.substring(startCodon, minIndex + 3);
                geneList.add(gene); // Adding elements works the same way with .add()
                startIndex = minIndex + 3;
            }
        }
        return geneList;
    }

    private int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }

    public void testGetAllGenes() {
        String dna = "ATGAAATGAAAA";
        ArrayList<String> genes = getAllGenes(dna);
        
        // Iterating over the ArrayList using a for-each loop
        if (genes.isEmpty()) {
            System.out.println("no genes found");
        } else {
            for (String gene : genes) {
                System.out.println(gene);
            }
        }
    }
}