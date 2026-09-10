import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FourthRatings {

    public FourthRatings() {
    }

    public double getAverageByID(String movieID, int minimalRaters) {
        double total = 0.0;
        int count = 0;

        for (Rater rater : RaterDatabase.getRaters()) {
            if (rater.hasRating(movieID)) {
                total += rater.getRating(movieID);
                count++;
            }
        }

        if (count >= minimalRaters) {
            return total / count;
        }

        return 0.0;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();

        for (String movieID : movies) {
            double average = getAverageByID(movieID, minimalRaters);

            if (average > 0.0) {
                averageRatings.add(new Rating(movieID, average));
            }
        }

        Collections.sort(averageRatings, Collections.reverseOrder());
        return averageRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(
            int minimalRaters, Filter filterCriteria) {

        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();

        for (String movieID : movies) {
            double average = getAverageByID(movieID, minimalRaters);

            if (average > 0.0) {
                averageRatings.add(new Rating(movieID, average));
            }
        }

        Collections.sort(averageRatings, Collections.reverseOrder());
        return averageRatings;
    }

    private double dotProduct(Rater me, Rater rater) {
        double product = 0.0;

        for (String movieID : me.getItemsRated()) {
            if (rater.hasRating(movieID)) {
                double myRating = me.getRating(movieID) - 5.0;
                double otherRating = rater.getRating(movieID) - 5.0;

                product += myRating * otherRating;
            }
        }

        return product;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);

        if (me == null) {
            return similarities;
        }

        for (Rater rater : RaterDatabase.getRaters()) {
            if (!rater.getID().equals(id)) {
                double similarity = dotProduct(me, rater);

                if (similarity > 0.0) {
                    similarities.add(
                        new Rating(rater.getID(), similarity)
                    );
                }
            }
        }

        Collections.sort(similarities, Collections.reverseOrder());
        return similarities;
    }

    public ArrayList<Rating> getSimilarRatings(
            String id, int numSimilarRaters, int minimalRaters) {

        return getSimilarRatingsByFilter(
            id,
            numSimilarRaters,
            minimalRaters,
            new TrueFilter()
        );
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(
            String id,
            int numSimilarRaters,
            int minimalRaters,
            Filter filterCriteria) {

        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        HashMap<String, Double> weightedTotals =
                new HashMap<String, Double>();

        HashMap<String, Double> similarityTotals =
                new HashMap<String, Double>();

        HashMap<String, Integer> ratingCounts =
                new HashMap<String, Integer>();

        int numberOfRaters =
                Math.min(numSimilarRaters, similarities.size());

        for (int i = 0; i < numberOfRaters; i++) {
            Rating similarity = similarities.get(i);

            String raterID = similarity.getItem();
            double weight = similarity.getValue();

            Rater rater = RaterDatabase.getRater(raterID);

            for (String movieID : movies) {
                if (rater.hasRating(movieID)) {
                    double rating = rater.getRating(movieID);

                    double weightedRating = weight * rating;

                    weightedTotals.put(
                        movieID,
                        weightedTotals.getOrDefault(movieID, 0.0)
                            + weightedRating
                    );

                    similarityTotals.put(
                        movieID,
                        similarityTotals.getOrDefault(movieID, 0.0)
                            + weight
                    );

                    ratingCounts.put(
                        movieID,
                        ratingCounts.getOrDefault(movieID, 0) + 1
                    );
                }
            }
        }

        ArrayList<Rating> results = new ArrayList<Rating>();

        for (String movieID : weightedTotals.keySet()) {
            int count = ratingCounts.get(movieID);

            if (count >= minimalRaters) {
                double similarityTotal = similarityTotals.get(movieID);

                double weightedAverage =
                        weightedTotals.get(movieID) / similarityTotal;

                results.add(new Rating(movieID, weightedAverage));
            }
        }

        Collections.sort(results, Collections.reverseOrder());
        return results;
    }
}