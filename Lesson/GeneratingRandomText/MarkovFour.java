import java.util.ArrayList;
import java.util.Random;

public class MarkovFour {
    private String myText;
    private Random myRandom;

    public MarkovFour() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;

        int index = myText.indexOf(key, start);

        while (index != -1) {
            int nextIndex = index + key.length();

            if (nextIndex < myText.length()) {
                follows.add(String.valueOf(myText.charAt(nextIndex)));
            }

            start = nextIndex;
            index = myText.indexOf(key, start);
        }

        return follows;
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length() - 4);
        System.out.println("START INDEX = " + index);

        String current = myText.substring(index, index + 4);
        sb.append(current);

        for (int k = 0; k < numChars - 4; k++) {
            ArrayList<String> follows = getFollows(current);

            if (follows.size() == 0) {
                break;
            }

            int nextIndex = myRandom.nextInt(follows.size());
            String next = follows.get(nextIndex);

            current = current.substring(1) + next;
            sb.append(next);
        }

        return sb.toString();
    }
}