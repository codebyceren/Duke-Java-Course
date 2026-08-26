import java.util.ArrayList;
import java.util.Random;

public class MarkovWordOne {

    private String[] myText;
    private Random myRandom;

    public MarkovWordOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    private int indexOf(String[] words, String target, int start) {
        for (int i = start; i < words.length; i++) {
            if (words[i].equals(target)) {
                return i;
            }
        }

        return -1;
    }

    public void testIndexOf() {
        String[] words = {
            "this", "is", "just", "a", "test",
            "yes", "this", "is", "a", "simple", "test"
        };

        System.out.println("this starting at 0: "
                + indexOf(words, "this", 0));

        System.out.println("this starting at 3: "
                + indexOf(words, "this", 3));

        System.out.println("frog starting at 0: "
                + indexOf(words, "frog", 0));

        System.out.println("frog starting at 5: "
                + indexOf(words, "frog", 5));

        System.out.println("simple starting at 2: "
                + indexOf(words, "simple", 2));

        System.out.println("test starting at 5: "
                + indexOf(words, "test", 5));
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();

        int start = 0;
        int index = indexOf(myText, key, start);

        while (index != -1) {

            if (index + 1 < myText.length) {
                follows.add(myText[index + 1]);
            }

            start = index + 1;
            index = indexOf(myText, key, start);
        }

        return follows;
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length);
        String current = myText[index];

        // GEÇİCİ KONTROL
        System.out.println("WORD COUNT = " + myText.length);
        System.out.println("START INDEX = " + index);
        System.out.println("START WORD = [" + current + "]");

        sb.append(current);

        for (int k = 1; k < numWords; k++) {

            ArrayList<String> follows = getFollows(current);

            if (follows.size() == 0) {
                break;
            }

            int nextIndex = myRandom.nextInt(follows.size());
            current = follows.get(nextIndex);

            sb.append(" ");
            sb.append(current);
        }

        return sb.toString();
    }
}