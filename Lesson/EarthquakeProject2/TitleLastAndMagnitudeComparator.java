import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();

        String lastWord1 = title1.substring(title1.lastIndexOf(" ") + 1);
        String lastWord2 = title2.substring(title2.lastIndexOf(" ") + 1);

        int result = lastWord1.compareTo(lastWord2);

        if (result != 0) {
            return result;
        }

        if (q1.getMagnitude() < q2.getMagnitude()) {
            return -1;
        }

        if (q1.getMagnitude() > q2.getMagnitude()) {
            return 1;
        }

        return 0;
    }
}