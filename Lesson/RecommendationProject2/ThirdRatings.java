import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        myRaters = loadRaters(ratingsfile);
    }

    private ArrayList<Rater> loadRaters(String filename) {

        ArrayList<Rater> raters =
            new ArrayList<Rater>();

        try {

            BufferedReader br =
                new BufferedReader(
                    new FileReader(
                        "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/RecommendationProject2/data/"
                        + filename
                    )
                );

            String line = br.readLine();

            while ((line = br.readLine()) != null) {

                String[] fields = line.split(",");

                String raterID = fields[0];
                String movieID = fields[1];
                double rating = Double.parseDouble(fields[2]);

                Rater rater =
                    findRater(raters, raterID);

                if (rater == null) {

                    rater =
                        new EfficientRater(raterID);

                    raters.add(rater);
                }

                rater.addRating(movieID, rating);
            }

            br.close();

        } catch (IOException e) {

            System.out.println(
                "Error reading ratings file: "
                + filename
            );

            e.printStackTrace();
        }

        return raters;
    }

    private Rater findRater(
        ArrayList<Rater> raters,
        String id
    ) {

        for (Rater rater : raters) {

            if (rater.getID().equals(id)) {
                return rater;
            }
        }

        return null;
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public ArrayList<Rating> getAverageRatings(
        int minimalRaters
    ) {

        ArrayList<String> movies =
            MovieDatabase.filterBy(
                new TrueFilter()
            );

        ArrayList<Rating> averageRatings =
            new ArrayList<Rating>();

        for (String movieID : movies) {

            double total = 0;
            int count = 0;

            for (Rater rater : myRaters) {

                if (rater.hasRating(movieID)) {

                    total += rater.getRating(movieID);
                    count++;
                }
            }

            if (count >= minimalRaters) {

                double average =
                    total / count;

                averageRatings.add(
                    new Rating(movieID, average)
                );
            }
        }

        return averageRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(
        int minimalRaters,
        Filter filterCriteria
    ) {

        ArrayList<String> movies =
            MovieDatabase.filterBy(filterCriteria);

        ArrayList<Rating> averageRatings =
            new ArrayList<Rating>();

        for (String movieID : movies) {

            double total = 0;
            int count = 0;

            for (Rater rater : myRaters) {

                if (rater.hasRating(movieID)) {

                    total += rater.getRating(movieID);
                    count++;
                }
            }

            if (count >= minimalRaters) {

                double average =
                    total / count;

                averageRatings.add(
                    new Rating(movieID, average)
                );
            }
        }

        return averageRatings;
    }
}