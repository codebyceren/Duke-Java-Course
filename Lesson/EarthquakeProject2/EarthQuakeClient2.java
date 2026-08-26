import java.util.*;

public class EarthQuakeClient2 {

    public EarthQuakeClient2() {
        // Constructor
    }

    public ArrayList<QuakeEntry> filter(
            ArrayList<QuakeEntry> quakeData, Filter f) {

        ArrayList<QuakeEntry> answer =
                new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }

        return answer;
    }

    // Assignment 1 - Test 1
    public void quakesWithFilter() {

    EarthQuakeParser parser =
            new EarthQuakeParser();

    String source =
            "Lesson/EarthquakeProject2/data/nov20quakedata.atom";

    ArrayList<QuakeEntry> list =
            parser.read(source);

    System.out.println(
            "read data for " + list.size() + " quakes");

    MagnitudeFilter magFilter =
            new MagnitudeFilter(4.0, 5.0);

    ArrayList<QuakeEntry> filtered =
            filter(list, magFilter);

    DepthFilter depthFilter =
            new DepthFilter(-35000.0, -12000.0);

    filtered = filter(filtered, depthFilter);

    System.out.println(
            "Found " + filtered.size() + " quakes");
}
    // Assignment 2 - Test 1
    public void testMatchAllFilter() {

    EarthQuakeParser parser =
            new EarthQuakeParser();

    String source =
            "Lesson/EarthquakeProject2/data/nov20quakedata.atom";

    ArrayList<QuakeEntry> list =
            parser.read(source);

    System.out.println(
            "read data for " + list.size() + " quakes");

    MagnitudeFilter magFilter =
            new MagnitudeFilter(0.0, 2.0);

    DepthFilter depthFilter =
            new DepthFilter(-100000.0, -10000.0);

    PhraseFilter phraseFilter =
            new PhraseFilter("any", "a");

    MatchAllFilter maf =
            new MatchAllFilter();

    maf.addFilter(magFilter);
    maf.addFilter(depthFilter);
    maf.addFilter(phraseFilter);

    ArrayList<QuakeEntry> filtered =
            filter(list, maf);

    System.out.println(
            "Found " + filtered.size() + " quakes");
}
    // Assignment 2 - Test 2
   public void testMatchAllFilter2() {

    EarthQuakeParser parser =
            new EarthQuakeParser();

    String source =
            "Lesson/EarthquakeProject2/data/nov20quakedata.atom";

    ArrayList<QuakeEntry> list =
            parser.read(source);

    System.out.println(
            "read data for " + list.size() + " quakes");

    MagnitudeFilter magFilter =
            new MagnitudeFilter(0.0, 3.0);

    Location tulsa =
            new Location(36.1314, -95.9372);

    DistanceFilter distanceFilter =
            new DistanceFilter(tulsa, 10000000);

    PhraseFilter phraseFilter =
            new PhraseFilter("any", "Ca");

    MatchAllFilter maf =
            new MatchAllFilter();

    maf.addFilter(magFilter);
    maf.addFilter(distanceFilter);
    maf.addFilter(phraseFilter);

    ArrayList<QuakeEntry> filtered =
            filter(list, maf);

    System.out.println(
            "Found " + filtered.size() + " quakes");
}

    public void createCSV() {

        EarthQuakeParser parser =
                new EarthQuakeParser();

        String source =
                "Lesson/EarthquakeProject2/data/nov20quakedatasmall.atom";

        ArrayList<QuakeEntry> list =
                parser.read(source);

        dumpCSV(list);

        System.out.println(
                "# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {

        System.out.println(
                "Latitude,Longitude,Magnitude,Info");

        for (QuakeEntry qe : list) {

            System.out.printf(
                    "%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }
    // Soru için eklenen metot (Billund, Denmark)
    public void testMatchAllFilterBillund() {

        EarthQuakeParser parser =
                new EarthQuakeParser();

        String source =
                "Lesson/EarthquakeProject2/data/nov20quakedata.atom";

        ArrayList<QuakeEntry> list =
                parser.read(source);

        System.out.println(
                "read data for " + list.size() + " quakes");

        MagnitudeFilter magFilter =
                new MagnitudeFilter(0.0, 5.0);

        // Billund, Denmark koordinatları ve 3,000,000 metre mesafe
        Location billund = new Location(55.7308, 9.1153);
        DistanceFilter distanceFilter =
                new DistanceFilter(billund, 3000000);

        // Başlığında "e" harfi geçenler ("any", "e")
        PhraseFilter phraseFilter =
                new PhraseFilter("any", "e");

        MatchAllFilter maf =
                new MatchAllFilter();

        maf.addFilter(magFilter);
        maf.addFilter(distanceFilter);
        maf.addFilter(phraseFilter);

        ArrayList<QuakeEntry> filtered =
                filter(list, maf);

        System.out.println(
                "Found " + filtered.size() + " quakes");
    }

    public static void main(String[] args) {

        EarthQuakeClient2 client =
                new EarthQuakeClient2();

        //client.testMatchAllFilter2();
        client.testMatchAllFilterBillund();
    }
}