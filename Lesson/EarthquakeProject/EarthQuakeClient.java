import java.util.ArrayList;

public class EarthQuakeClient {

    public ArrayList<QuakeEntry> filterByDepth(
            ArrayList<QuakeEntry> quakeData,
            double minDepth,
            double maxDepth) {

        ArrayList<QuakeEntry> answer =
                new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            double depth = qe.getDepth();

            if (depth > minDepth && depth < maxDepth) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(
            ArrayList<QuakeEntry> quakeData,
            String phrase,
            String where) {

        ArrayList<QuakeEntry> answer =
                new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {

            String title = qe.getInfo();

            if (where.equals("start") &&
                    title.startsWith(phrase)) {
                answer.add(qe);
            }

            else if (where.equals("end") &&
                    title.endsWith(phrase)) {
                answer.add(qe);
            }

            else if (where.equals("any") &&
                    title.contains(phrase)) {
                answer.add(qe);
            }
        }

        return answer;
    }

    // Question 1
    public void quakesOfDepth() {

        EarthQuakeParser parser =
                new EarthQuakeParser();

        String source =
                "Lesson/EarthquakeProject2/data/nov20quakedata.atom";

        ArrayList<QuakeEntry> list =
                parser.read(source);

        ArrayList<QuakeEntry> quakes =
                filterByDepth(
                        list,
                        -4000.0,
                        -2000.0
                );

        System.out.println(
                "Found " + quakes.size() + " quakes"
        );
    }

    // Question 2
    public void quakesByPhrase() {

        EarthQuakeParser parser =
                new EarthQuakeParser();

        String source =
                "Lesson/EarthquakeProject2/data/nov20quakedata.atom";

        ArrayList<QuakeEntry> list =
                parser.read(source);

        ArrayList<QuakeEntry> quakes =
                filterByPhrase(
                        list,
                        "Alaska",
                        "end"
                );

        for (QuakeEntry qe : quakes) {
            System.out.println(qe);
        }

        System.out.println(
                "Found " + quakes.size()
                + " quakes that match " + "Can" + " at " + "any"
)       ;
    }

    public static void main(String[] args) {

        EarthQuakeClient client =
                new EarthQuakeClient();

        // Question 1
        //client.quakesOfDepth();

        // Question 2
        client.quakesByPhrase();
        

        
        
    }
}