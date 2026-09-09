import java.io.IOException;
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("Lesson/RecommendationProject/data/ratedmoviesfull.csv", "Lesson/RecommendationProject/data/ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile) {

        FirstRatings fr = new FirstRatings();

        try {
            myMovies = fr.loadMovies(moviefile);
            myRaters = fr.loadRaters(ratingsfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public double getAverageByID(String id, int minimalRaters) {
        double total = 0.0;
        int count = 0;

        for (Rater rater : myRaters) {
            if (rater.hasRating(id)) {
                total += rater.getRating(id);
                count++;
            }
        }

        if (count >= minimalRaters) {
            return total / count;
        } else {
            return 0.0;
        }
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {

        ArrayList<Rating> averageRatings = new ArrayList<Rating>();

        for (Movie movie : myMovies) {

            String movieID = movie.getID();

            double average =
                getAverageByID(movieID, minimalRaters);

            if (average != 0.0) {

                Rating rating =
                    new Rating(movieID, average);

                averageRatings.add(rating);
            }
        }

        return averageRatings;
    }

    public String getTitle(String id) {

        for (Movie movie : myMovies) {

            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "ID was not found";
    }

    public int countMoviesWithAtLeast50Ratings() {

        int count = 0;

        for (Movie movie : myMovies) {

            int ratingCount = 0;

            for (Rater rater : myRaters) {

                if (rater.hasRating(movie.getID())) {
                    ratingCount++;
                }
            }

            if (ratingCount >= 50) {
                count++;
            }
        }

        return count;
    }

    public String getID(String title) {

        for (Movie movie : myMovies) {

            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }   

        return "NO SUCH TITLE";
    }
}