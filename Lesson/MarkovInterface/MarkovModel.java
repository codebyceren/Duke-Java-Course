public class MarkovModel extends AbstractMarkovModel {

    private int myOrder;

    public MarkovModel(int N) {
        super();
        myOrder = N;
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length() - myOrder);
        System.out.println("START INDEX = " + index);

        String current = myText.substring(index, index + myOrder);
        sb.append(current);

        for (int k = 0; k < numChars - myOrder; k++) {
            java.util.ArrayList<String> follows = getFollows(current);

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
        return "MarkovModel of order " + myOrder;
    }
}