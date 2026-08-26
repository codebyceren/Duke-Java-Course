import java.util.ArrayList;
import java.util.Collections;

public class DifferentSorters {

    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();

        String source = "Lesson/EarthquakeProject2/data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        Collections.sort(list);

        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

        System.out.println(list.get(600));
    }

    public void sortByTitleAndDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();

        String source = "Lesson/EarthquakeProject2/data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        Collections.sort(list, new TitleAndDepthComparator());

        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

        System.out.println(list.get(500));
    }


    public void sortByLastWordInTitleThenByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();

        String source = "Lesson/EarthquakeProject2/data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        Collections.sort(list, new TitleLastAndMagnitudeComparator());

        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

        System.out.println(list.get(500));
    }

    public static void main(String[] args) {
        DifferentSorters ds = new DifferentSorters();
        //ds.sortWithCompareTo();
        

        //ds.sortByTitleAndDepth();

        ds.sortByLastWordInTitleThenByMagnitude();
    }
}