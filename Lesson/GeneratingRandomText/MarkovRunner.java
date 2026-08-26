import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class MarkovRunner {

    public void runMarkovZero() {
        try {
            String filePath = "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/Markov/data/confucius.txt";

            String st = Files.readString(Path.of(filePath));
            // Satır sonu ve ekstra boşluk uyumluluğu sağlandı
            st = st.replace('\r', ' ').replace('\n', ' ');

            MarkovZero markov = new MarkovZero();
            markov.setRandom(1024);
            markov.setTraining(st);

            for (int k = 0; k < 3; k++) {
                String text = markov.getRandomText(500);
                printOut(text);
            }

        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }

    public void runMarkovOne() {
        try {
            String filePath = "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/GeneratingRandomText/data/romeo.txt";

            String st = Files.readString(Path.of(filePath));
            st = st.replace('\r', ' ').replace('\n', ' ');

            MarkovOne markov = new MarkovOne();

            markov.setRandom(365);
            

            markov.setTraining(st);

            for (int k = 0; k < 3; k++) {
                String text = markov.getRandomText(500);
                printOut(text);
            }

        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }

    public void runMarkovFour() {
        try {
            String filePath = "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/GeneratingRandomText/data/romeo.txt";

            String st = Files.readString(Path.of(filePath));
            st = st.replace('\r', ' ').replace('\n', ' ');

            MarkovFour markov = new MarkovFour();

            markov.setRandom(715);
            markov.setTraining(st);

            for (int k = 0; k < 3; k++) {
                String text = markov.getRandomText(500);
                printOut(text);
            }

        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }

    public void runMarkovModel() {
        try {
            String filePath = "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/GeneratingRandomText/data/romeo.txt";

            String st = Files.readString(Path.of(filePath));
            st = st.replace('\r', ' ').replace('\n', ' ');

            MarkovModel markov = new MarkovModel(7);

            markov.setRandom(953);
            markov.setTraining(st);

            for (int k = 0; k < 3; k++) {
                String text = markov.getRandomText(500);
                printOut(text);
            }

        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }

    public void runMarkov() {
        try {
            String filePath = "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/GeneratingRandomText/data/romeo.txt";

            String st = Files.readString(Path.of(filePath));
            st = st.replace('\r', ' ').replace('\n', ' ');

            MarkovWordOne markov = new MarkovWordOne();

            markov.setRandom(139);
            markov.setTraining(st);

            String text = markov.getRandomText(120);

            printOut(text);

        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }

    public void runMarkovTwo() {
        try {
            String filePath = "data/confucius.txt";

            String st = Files.readString(Path.of(filePath));
            st = st.replace('\r', ' ').replace('\n', ' ');

            MarkovWordTwo markov = new MarkovWordTwo();

            markov.setRandom(549);
            markov.setTraining(st);

            String text = markov.getRandomText(120);

            printOut(text);

        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
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
        //runner.runMarkovZero();

        runner.runMarkovOne();
        //runner.runMarkovFour();
        //runner.runMarkovModel();
        // runner.runMarkov();
        // runner.runMarkovTwo();
    }
}