import java.util.ArrayList;

public class QuakeSortInPlace {

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {

        int largestIndex = from;

        for (int i = from + 1; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(largestIndex).getDepth()) {
                largestIndex = i;
            }
        }

        return largestIndex;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {

        //for (int i = 0; i < in.size() - 1; i++) {
        for (int i = 0; i < 50 ; i++){


            int largestIndex = getLargestDepth(in, i);

            QuakeEntry temp = in.get(i);
            in.set(i, in.get(largestIndex));
            in.set(largestIndex, temp);
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {

        // Compare adjacent earthquakes
        for (int i = 0; i < quakeData.size() - 1 - numSorted; i++) {

            // Swap if the earthquakes are out of magnitude order
            if (quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude()) {

                QuakeEntry temp = quakeData.get(i);
                quakeData.set(i, quakeData.get(i + 1));
                quakeData.set(i + 1, temp);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {

        // Make N - 1 bubble sort passes
        for (int numSorted = 0; numSorted < in.size() - 1; numSorted++) {

            // Perform one pass of bubble sort
            onePassBubbleSort(in, numSorted);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {

        for (int i = 0; i < quakes.size() - 1; i++) {

            if (quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude()) {
                return false;
            }
        }

        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {

        int passes = 0;

        for (int numSorted = 0; numSorted < in.size() - 1; numSorted++) {

            onePassBubbleSort(in, numSorted);
            passes++;

            if (checkInSortedOrder(in)) {
                break;
            }
        }

        System.out.println("It took " + passes + " passes to sort the elements.");
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {

        int passes = 0;

        for (int i = 0; i < in.size() - 1; i++) {

            int smallestIndex = i;

            for (int j = i + 1; j < in.size(); j++) {
                if (in.get(j).getMagnitude() < in.get(smallestIndex).getMagnitude()) {
                    smallestIndex = j;
                }
            }

            QuakeEntry temp = in.get(i);
            in.set(i, in.get(smallestIndex));
            in.set(smallestIndex, temp);

            passes++;

            if (checkInSortedOrder(in)) {
                break;
            }
        }

        System.out.println("It took " + passes + " passes to sort the elements.");
    }

    public void testSort() {

        EarthQuakeParser parser = new EarthQuakeParser();

        String source =
            "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/EarthquakeProject2/data/earthQuakeDataWeekDec6sample1.atom";

        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");

        // Assignment 1
        //sortByLargestDepth(list);

        // Assignment 2
        // sortByMagnitudeWithBubbleSort(list);

        // Assignment 3 - Bubble Sort
        sortByMagnitudeWithBubbleSortWithCheck(list);

        // Assignment 3 - Selection Sort
        //sortByMagnitudeWithCheck(list);

        //System.out.println("EarthQuakes in sorted order:");

        //for (QuakeEntry quake : list) {
            //System.out.println(quake);
        //}
    }

    public static void main(String[] args) {

        QuakeSortInPlace sorter = new QuakeSortInPlace();
        sorter.testSort();

        
    }
}