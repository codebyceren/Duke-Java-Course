import java.util.ArrayList;

public class Tester {

    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();

        markov.setTraining("this is a test yes this is a test.");

        System.out.println("t: " + markov.getFollows("t"));
        System.out.println("t size: " + markov.getFollows("t").size());

        System.out.println("e: " + markov.getFollows("e"));
        System.out.println("e size: " + markov.getFollows("e").size());

        System.out.println("es: " + markov.getFollows("es"));
        System.out.println("es size: " + markov.getFollows("es").size());

        System.out.println(".: " + markov.getFollows("."));
        System.out.println(". size: " + markov.getFollows(".").size());

        System.out.println("t.: " + markov.getFollows("t."));
        System.out.println("t. size: " + markov.getFollows("t.").size());
    }

    public void testGetFollowsWithFile() {
        try {
            String filePath =
                "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/Markov/data/confucius.txt";

            String text = java.nio.file.Files.readString(
                java.nio.file.Path.of(filePath)
            );

            MarkovOne markov = new MarkovOne();
            markov.setTraining(text);

            // Q3
            ArrayList<String> followsO = markov.getFollows("o");
            System.out.println("Size for 'o': " + followsO.size());

            // Q4
            ArrayList<String> followsHE = markov.getFollows("he");
            System.out.println("Size for 'he': " + followsHE.size());

        } catch (java.io.IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }

    public void testMarkovWordTwo() {
        MarkovWordTwo markov = new MarkovWordTwo();

        markov.setTraining(
            "this is just a test yes this is a simple test"
        );

        System.out.println(
            "this is: " + markov.getFollows("this", "is")
        );

        System.out.println(
            "just a: " + markov.getFollows("just", "a")
        );

        System.out.println(
            "is just: " + markov.getFollows("is", "just")
        );

        System.out.println(
            "test yes: " + markov.getFollows("test", "yes")
        );
    }

    public static void main(String[] args) {
        Tester tester = new Tester();

        tester.testGetFollowsWithFile();
    }
}