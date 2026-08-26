import java.util.*;

public class MarkovWord implements IMarkovModel {

    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    private int indexOf(String[] words, WordGram target, int start) {

        for (int i = start; i <= words.length - target.length(); i++) {

            WordGram current = new WordGram(words, i, target.length());

            if (current.equals(target)) {
                return i;
            }
        }

        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram) {

        ArrayList<String> follows = new ArrayList<String>();

        int start = 0;

        while (true) {

            int index = indexOf(myText, kGram, start);

            if (index == -1) {
                break;
            }

            int followIndex = index + kGram.length();

            if (followIndex < myText.length) {
                follows.add(myText[followIndex]);
            }

            start = index + 1;
        }

        return follows;
    }

    public String getRandomText(int numWords) {

        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length - myOrder);

        WordGram current = new WordGram(myText, index, myOrder);

        sb.append(current);

        for (int k = 0; k < numWords - myOrder; k++) {

            ArrayList<String> follows = getFollows(current);

            if (follows.size() == 0) {
                break;
            }

            int nextIndex = myRandom.nextInt(follows.size());

            String next = follows.get(nextIndex);

            sb.append(" ");
            sb.append(next);

            current = current.shiftAdd(next);
        }

        return sb.toString();
    }

    public String toString() {
        return "MarkovWord of order " + myOrder;
    }
}