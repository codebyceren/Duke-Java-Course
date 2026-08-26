import java.util.ArrayList;

public class MarkovOne extends AbstractMarkovModel {

    public MarkovOne() {
        super();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length());
        System.out.println("START INDEX = " + index);

        String current = String.valueOf(myText.charAt(index));
        sb.append(current);

        for (int k = 0; k < numChars - 1; k++) {
            ArrayList<String> follows = getFollows(current);

            if (follows.size() == 0) {
                break;
            }

            int nextIndex = myRandom.nextInt(follows.size());
            current = follows.get(nextIndex);

            sb.append(current);
        }

        return sb.toString();
    }

    public String toString() {
        return "MarkovModel of order 1";
    }
}