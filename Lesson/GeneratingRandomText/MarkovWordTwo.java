import java.util.ArrayList;
import java.util.Random;

public class MarkovWordTwo {

    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    private int indexOf(String[] words,
                        String target1,
                        String target2,
                        int start) {

        for (int i = start; i < words.length - 1; i++) {

            if (words[i].equals(target1)
                    && words[i + 1].equals(target2)) {
                return i;
            }
        }

        return -1;
    }

    public ArrayList<String> getFollows(String key1, String key2) {

        ArrayList<String> follows = new ArrayList<String>();

        int start = 0;

        int index = indexOf(myText, key1, key2, start);

        while (index != -1) {

            if (index + 2 < myText.length) {
                follows.add(myText[index + 2]);
            }

            start = index + 1;

            index = indexOf(myText, key1, key2, start);
        }

        return follows;
    }

    public String getRandomText(int numWords) {

        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length - 1);

        String key1 = myText[index];
        String key2 = myText[index + 1];

        sb.append(key1);
        sb.append(" ");
        sb.append(key2);

        for (int k = 0; k < numWords - 2; k++) {

            ArrayList<String> follows =
                    getFollows(key1, key2);

            if (follows.size() == 0) {
                break;
            }

            int nextIndex =
                    myRandom.nextInt(follows.size());

            String nextWord = follows.get(nextIndex);

            sb.append(" ");
            sb.append(nextWord);

            key1 = key2;
            key2 = nextWord;
        }

        return sb.toString();
    }
}