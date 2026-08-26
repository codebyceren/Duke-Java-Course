import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class MarkovRunner {

    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);

        System.out.println("running with " + markov);

        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);

        System.out.println("running with " + markov);

        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        String st = readFile("/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/Markov/data/confucius.txt");

        MarkovWord markovWord = new MarkovWord(5);

        runModel(markovWord, st, 200, 844);
    }

    public void testHashMap() {
        String text = readFile("/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/Markov/data/confucius.txt");

        EfficientMarkovWord markov = new EfficientMarkovWord(6);

        markov.setRandom(792);
        markov.setTraining(text);

        markov.printHashMapInfo();
    }

    public void compareMethods() {
        String text = readFile("data/hawthorne.txt");

        int order = 2;
        int size = 100;
        int seed = 42;

        MarkovWord markovWord = new MarkovWord(order);

        long start = System.nanoTime();

        runModel(markovWord, text, size, seed);

        long end = System.nanoTime();

        double elapsedMarkov = (end - start) / 1_000_000_000.0;

        System.out.println("MarkovWord time: " + elapsedMarkov + " seconds");

        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(order);

        start = System.nanoTime();

        runModel(efficientMarkovWord, text, size, seed);

        end = System.nanoTime();

        double elapsedEfficient = (end - start) / 1_000_000_000.0;

        System.out.println("EfficientMarkovWord time: " + elapsedEfficient + " seconds");
    }

    private String readFile(String fileName) {
        try {
            String text = new String(Files.readAllBytes(Paths.get(fileName)));
            return text.replace('\n', ' ').replace('\r', ' ');
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + fileName, e);
        }
    }

    private void printOut(String s) {
        String[] words = s.split("\\s+");

        int psize = 0;

        System.out.println("----------------------------------");

        for (int k = 0; k < words.length; k++) {
            System.out.print(words[k] + " ");

            psize += words[k].length() + 1;

            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }

        System.out.println("\n----------------------------------");
    }

    public static void main(String[] args) {
        MarkovRunner runner = new MarkovRunner();
        // runner.compareMethods();

        //runner.runMarkov();

        runner.testHashMap();
    }
}