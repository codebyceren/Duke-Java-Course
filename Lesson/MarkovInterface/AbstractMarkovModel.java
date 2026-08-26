import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractMarkovModel implements IMarkovModel {

    protected String myText;
    protected Random myRandom;

    public AbstractMarkovModel() {
        myRandom = new Random();
    }

    public void setTraining(String text) {
        myText = text;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();

        int start = 0;
        int index = myText.indexOf(key, start);

        while (index != -1) {
            int nextIndex = index + key.length();

            if (nextIndex < myText.length()) {
                follows.add(String.valueOf(myText.charAt(nextIndex)));
            }

            start = nextIndex + 1;
            index = myText.indexOf(key, start);
        }

        return follows;
    }

    public abstract String getRandomText(int numChars);
}