import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {

    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;

    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        myMap = new HashMap<WordGram, ArrayList<String>>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
    }

    private void buildMap() {

        myMap.clear();

        for (int i = 0; i <= myText.length - myOrder; i++) {

            WordGram key = new WordGram(myText, i, myOrder);

            if (!myMap.containsKey(key)) {
                myMap.put(key, new ArrayList<String>());
            }

            if (i + myOrder < myText.length) {
                String follow = myText[i + myOrder];
                myMap.get(key).add(follow);
            }
        }
    }

    public ArrayList<String> getFollows(WordGram kGram) {

        if (myMap.containsKey(kGram)) {
            return myMap.get(kGram);
        }

        return new ArrayList<String>();
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

    public void printHashMapInfo() {

        int maxSize = 0;

        for (WordGram key : myMap.keySet()) {
            int size = myMap.get(key).size();

            if (size > maxSize) {
                maxSize = size;
            }
        }

        System.out.println("Map size: " + myMap.size());
        System.out.println("Max value size: " + maxSize);

        System.out.println("Keys with max value size:");

        for (WordGram key : myMap.keySet()) {

            if (myMap.get(key).size() == maxSize) {
                System.out.println(key);
            }
        }
    }

    public String toString() {
        return "EfficientMarkovWord of order " + myOrder;
    }
}