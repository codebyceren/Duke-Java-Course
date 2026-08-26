package Lesson;

public class PrintAllGenesWithWhile {

    // Helper method using && operator in the while loop condition
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        
        while (currIndex != -1 && (currIndex - startIndex) % 3 != 0) {
            currIndex = dna.indexOf(stopCodon, currIndex + 1);
        }
        
        if (currIndex == -1) {
            return dna.length();
        }
        return currIndex;
    }

    // Main method using while (true), break, and gene.isEmpty() check
    public void printAllGenesWithWhile(String dna) {
        System.out.println("Testing DNA: " + dna);
        int startIndex = 0;
        int geneCount = 0;

        while (true) {
            int currIndex = dna.indexOf("ATG", startIndex);
            
            // If no more ATG is found, break out of the loop
            if (currIndex == -1) {
                break;
            }
            
            int taaIndex = findStopCodon(dna, currIndex, "TAA");
            int tagIndex = findStopCodon(dna, currIndex, "TAG");
            int tgaIndex = findStopCodon(dna, currIndex, "TGA");
            
            int stopIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
            
            if (stopIndex == dna.length()) {
                startIndex = currIndex + 1;
            } else {
                String gene = dna.substring(currIndex, stopIndex + 3);
                
                if (gene.isEmpty()) {
                    System.out.println("Empty gene encountered.");
                } else {
                    System.out.println("Gene found: " + gene);
                    geneCount++;
                }
                
                startIndex = stopIndex + 3;
            }
        }
        
        if (geneCount == 0) {
            System.out.println("No genes found.");
        }
        
        System.out.println("--------------------");
    }

    // Test method to run multiple test cases
    public void testPrintAllGenes() {
        printAllGenesWithWhile("CCATGAAATAAGCG");
        printAllGenesWithWhile("CCATGTTTTAGCG");
        printAllGenesWithWhile("CCATGCCCTGACGC");
        printAllGenesWithWhile("ATGAAATAGCCATGTGA");
        printAllGenesWithWhile("ATCGATCGATCG"); // Test with no genes
    }

    public static void main(String[] args) {
        PrintAllGenesWithWhile finder = new PrintAllGenesWithWhile();
        finder.testPrintAllGenes();
    }
}