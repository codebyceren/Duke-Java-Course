import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;

public class RaterDatabase {
    private static HashMap<String, Rater> ourRaters;

    private static void initialize() {
        if (ourRaters == null) {
            ourRaters = new HashMap<String, Rater>();
        }
    }

    public static void initialize(String filename) {
        if (ourRaters == null) {
            ourRaters = new HashMap<String, Rater>();
            String path = filename;
            File f = new File(path);
            if (!f.exists()) {
                path = "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/RecommendationProject3/data/" + filename;
            }
            addRatings(path);
        }
    }

    public static void addRatings(String filename) {
        initialize();
        try {
            List<String> lines = Files.readAllLines(Path.of(filename));
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts.length >= 3) {
                    String id = parts[0];
                    String item = parts[1];
                    double rating = Double.parseDouble(parts[2]);
                    addRaterRating(id, item, rating);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading ratings file: " + e.getMessage());
        }
    }

    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize();
        Rater rater;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID);
        } else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID, rater);
        }
        rater.addRating(movieID, rating);
    }

    public static Rater getRater(String id) {
        initialize();
        return ourRaters.get(id);
    }

    public static ArrayList<Rater> getRaters() {
        initialize();
        return new ArrayList<Rater>(ourRaters.values());
    }

    public static int size() {
        initialize();
        return ourRaters.size();
    }
}