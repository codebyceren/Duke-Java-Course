import java.util.ArrayList;

public class PlainRater implements Rater {

    private String myID;
    private ArrayList<Rating> myRatings;

    public PlainRater(String id) {
        myID = id;
        myRatings = new ArrayList<Rating>();
    }

    @Override
    public void addRating(String item, double rating) {
        myRatings.add(new Rating(item, rating));
    }

    @Override
    public boolean hasRating(String item) {
        for (Rating r : myRatings) {
            if (r.getItem().equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getID() {
        return myID;
    }

    @Override
    public double getRating(String item) {
        for (Rating r : myRatings) {
            if (r.getItem().equals(item)) {
                return r.getValue();
            }
        }
        return -1.0;
    }

    @Override
    public int numRatings() {
        return myRatings.size();
    }

    @Override
    public ArrayList<String> getItemsRated() {
        ArrayList<String> items = new ArrayList<String>();

        for (Rating r : myRatings) {
            items.add(r.getItem());
        }

        return items;
    }
}