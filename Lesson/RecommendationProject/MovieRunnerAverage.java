import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

    public void printAverageRatings() {

        SecondRatings sr = new SecondRatings(
            "data/ratedmoviesfull.csv",
            "data/ratings.csv"
        );

        System.out.println("Number of movies: "
            + sr.getMovieSize());

        System.out.println("Number of raters: "
            + sr.getRaterSize());

        ArrayList<Rating> ratings =
            sr.getAverageRatings(3);

        Collections.sort(ratings);

        for (Rating rating : ratings) {

            String title = sr.getTitle(rating.getItem());

            System.out.println(
                rating.getValue() + " " + title
            );
        }

        ArrayList<Rating> ratings50 =
            sr.getAverageRatings(50);

        System.out.println(
            "Movies with 50 or more ratings: "
            + ratings50.size()
        );
    }


    public void getAverageRatingOneMovie() {

        SecondRatings sr = new SecondRatings(
            "data/ratedmoviesfull.csv",
            "data/ratings.csv"
        );

        String title = "The Godfather";

        String id = sr.getID(title);

        ArrayList<Rating> ratings =
            sr.getAverageRatings(1);

        for (Rating rating : ratings) {

            if (rating.getItem().equals(id)) {

                System.out.println(
                    "Average rating for " + title + ": "
                    + rating.getValue()
                );
            }
        }
    }

    public static void main(String[] args) {

        MovieRunnerAverage runner = new MovieRunnerAverage();

        runner.printAverageRatings();
        
    }
}