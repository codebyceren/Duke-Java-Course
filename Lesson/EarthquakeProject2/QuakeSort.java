import java.util.ArrayList;

public class QuakeSort {

    public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes) {

        QuakeEntry smallest = quakes.get(0);

        for(QuakeEntry quake : quakes ) {
            if(quake.getMagnitude() < smallest.getMagnitude()) {
                smallest = quake;

            }
        }
        return smallest;
    }

    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> quakes) {

        ArrayList<QuakeEntry> sorted = new ArrayList<QuakeEntry>();
        
        while(!quakes.isEmpty()) {
            QuakeEntry smallest = getSmallestMagnitude(quakes);

            sorted.add(smallest);
            quakes.remove(smallest);
        }
        return sorted;
        
    } 
}