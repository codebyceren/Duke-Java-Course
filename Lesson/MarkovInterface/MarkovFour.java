import java.util.ArrayList;

public class MarkovFour extends AbstractMarkovModel {

    public MarkovFour() {
        super();
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

    public String toString() {
        return "MarkovModel of order 4";
    }
}