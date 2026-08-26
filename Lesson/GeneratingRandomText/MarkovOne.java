import java.util.ArrayList;
import java.util.Random;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

  
    public void setTraining(String s) {
        myText = s;
    }

 
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;

        while (true) {
            int index = myText.indexOf(key, start);

            if (index == -1 || index + key.length() >= myText.length()) {
                break;
            }

            
            String next = myText.substring(index + key.length(), index + key.length() + 1);
            follows.add(next);

            start = index + 1;
        }

        return follows;
    }

    public String getRandomText(int numChars) {
        if (myText == null || myText.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length() - 1);
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
}