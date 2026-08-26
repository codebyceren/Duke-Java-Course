package Lesson;
public class FindGeneSimple {

    public String findGeneSimple(String dna) {
        String result = "";
        
        // Start codon "ATG"
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return ""; 
        }
        
        // Stop codon "TAA"
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if (stopIndex == -1) {
            return ""; 
        }
        
        result = dna.substring(startIndex, stopIndex + 3);
        return result;
    }

    public void testFindGeneSimple() {
        String dna1 = "AATGCGTAATATGGT";
        String dna2 = "CGATGGTTTAAAAGT";
        String dna3 = "ATGCGTTAAGGTA";
        String dna4 = "ATGTAA";
        
        System.out.println("DNA strand: " + dna1);
        System.out.println("Gene found: " + findGeneSimple(dna1));
        
        System.out.println("DNA strand: " + dna2);
        System.out.println("Gene found: " + findGeneSimple(dna2));
        
        System.out.println("DNA strand: " + dna3);
        System.out.println("Gene found: " + findGeneSimple(dna3));
        
        System.out.println("DNA strand: " + dna4);
        System.out.println("Gene found: " + findGeneSimple(dna4));
    }

    public static void main(String[] args) {
        FindGeneSimple finder = new FindGeneSimple();
        finder.testFindGeneSimple();
    }
}