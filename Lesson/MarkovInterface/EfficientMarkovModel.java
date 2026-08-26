import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel {

    private int myOrder;
    private HashMap<String, ArrayList<String>> myMap;

    public EfficientMarkovModel(int N) {
        super();
        myOrder = N;
        myMap = new HashMap<String, ArrayList<String>>();
    }

    public String toString() {
        return "EfficientMarkovModel of order " + myOrder;
    }

    @Override
    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }

    private void buildMap() {
        myMap.clear();

        for (int i = 0; i <= myText.length() - myOrder; i++) {

            String key = myText.substring(i, i + myOrder);

            if (!myMap.containsKey(key)) {
                myMap.put(key, new ArrayList<String>());
            }

            int nextIndex = i + myOrder;

            if (nextIndex < myText.length()) {
                String next = String.valueOf(myText.charAt(nextIndex));
                myMap.get(key).add(next);
            }
        }
    }

    @Override
    protected ArrayList<String> getFollows(String key) {
        if (myMap.containsKey(key)) {
            return myMap.get(key);
        }

        return new ArrayList<String>();
    }

    @Override
    public String getRandomText(int numChars) {

        if (myText == null || myText.length() <= myOrder) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length() - myOrder);

        String current = myText.substring(index, index + myOrder);
        sb.append(current);

        for (int k = 0; k < numChars - myOrder; k++) {

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

    public void printHashMapInfo() {

        //if (myMap.size() < 30) {
            //System.out.println("HashMap: " + myMap);
        //}

        System.out.println("Map size: " + myMap.size());

        int maxSize = 0;

        for (String key : myMap.keySet()) {
            int size = myMap.get(key).size();

            if (size > maxSize) {
                maxSize = size;
            }
        }

        System.out.println("Max value size: " + maxSize);

        System.out.println("Keys with max value size:");

        for (String key : myMap.keySet()) {
            if (myMap.get(key).size() == maxSize) {
                System.out.println(key);
            }
        }
    }
}
