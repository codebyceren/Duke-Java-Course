import java.util.ArrayList;

public class LargestQuakes {

    public void findLargestQuakes() {

        EarthQuakeParser parser = new EarthQuakeParser();

        ArrayList<QuakeEntry> list =
            parser.read("Lesson/EarthquakeProject2/data/nov20quakedata.atom");
        System.out.println(list.size());

        ArrayList<QuakeEntry> largest = getLargest(list, 20);

        for(QuakeEntry qe : largest) {
            System.out.println(qe);
        }
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int largestIndex = 0;

        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).getMagnitude() > data.get(largestIndex).getMagnitude()) {
                largestIndex = i;
            }
        }

        return largestIndex;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {

        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);

        for(int i = 0; i < howMany ; i++) {
           int largestIndex = indexOfLargest(copy);
           answer.add(copy.get(largestIndex));
           copy.remove(largestIndex);
        }

        return answer;
    }

    public static void main(String[] args) {
        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes();

        
    }
}