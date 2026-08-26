import java.nio.file.Files;
import java.nio.file.Paths;

public class MarkovRunnerWithInterface {

    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);

        System.out.println("running with " + markov);

        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void testRomeo() {
        try {
            String text = Files.readString(
                Paths.get("/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/GeneratingRandomText/data/romeo.txt")
            );

            text = text.replace('\n', ' ');

            EfficientMarkovModel model = new EfficientMarkovModel(5);

            runModel(model, text, 200, 615);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runMarkov() {
        try {
            String st = Files.readString(
                Paths.get("/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/GeneratingRandomText/data/hawthorne.txt")
            );

            st = st.replace('\n', ' ');

            int size = 200;

            MarkovZero mz = new MarkovZero();
            runModel(mz, st, size, 42);

            MarkovOne mOne = new MarkovOne();
            runModel(mOne, st, size, 42);

            MarkovModel mThree = new MarkovModel(3);
            runModel(mThree, st, size, 42);

            MarkovFour mFour = new MarkovFour();
            runModel(mFour, st, size, 42);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testHashMap() {
        String text = "yes-this-is-a-thin-pretty-pink-thistle";

        EfficientMarkovModel model =
            new EfficientMarkovModel(2);

        this.runModel(model, text, 50, 42);
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

    public void compareMethods() {
        try {
            String st = Files.readString(
                Paths.get("/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/GeneratingRandomText/data/romeo.txt")
            );

            st = st.replace('\n', ' ');

            int size = 1000;
            int seed = 42;

            MarkovModel normal = new MarkovModel(2);

            long start = System.nanoTime();

            runModel(normal, st, size, seed);

            long end = System.nanoTime();

            System.out.println(
                "Time for MarkovModel: "
                + (end - start) / 1_000_000.0
                + " ms"
            );

            EfficientMarkovModel efficient =
                new EfficientMarkovModel(2);

            start = System.nanoTime();

            runModel(efficient, st, size, seed);

            end = System.nanoTime();

            System.out.println(
                "Time for EfficientMarkovModel: "
                + (end - start) / 1_000_000.0
                + " ms"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MarkovRunnerWithInterface runner =
            new MarkovRunnerWithInterface();

        // runner.testHashMap();

        // runner.compareMethods();

        runner.testRomeo();
    }
}