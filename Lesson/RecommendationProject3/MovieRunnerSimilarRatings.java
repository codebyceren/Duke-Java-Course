import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

    public void printSimilarRatings() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        ArrayList<Rating> ratings = fr.getSimilarRatings("71", 20, 5);
        System.out.println("Found " + ratings.size() + " movies");
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenre() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        GenreFilter filter = new GenreFilter("Mystery");
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("964", 20, 5, filter);
        System.out.println("Found " + ratings.size() + " movies");
        for (Rating rating : ratings) {
            String id = rating.getItem();
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(id));
            System.out.println("    " + MovieDatabase.getGenres(id));
        }
    }

    public void printSimilarRatingsByDirector() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        DirectorsFilter filter = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("120", 10, 2, filter);
        System.out.println("Found " + ratings.size() + " movies");
        for (Rating rating : ratings) {
            String id = rating.getItem();
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(id));
            System.out.println("    " + MovieDatabase.getDirector(id));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        AllFilters filters = new AllFilters();
        filters.addFilter(new GenreFilter("Drama"));
        filters.addFilter(new MinutesFilter(80, 160));

        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("168", 10, 3, filters);
        System.out.println("Found " + ratings.size() + " movies");
        for (Rating rating : ratings) {
            String id = rating.getItem();
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(id) + " " + MovieDatabase.getTitle(id));
            System.out.println("    " + MovieDatabase.getGenres(id));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        AllFilters filters = new AllFilters();
        filters.addFilter(new YearsAfterFilter(1975));
        filters.addFilter(new MinutesFilter(70, 200));

        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("314", 10, 5, filters);
        System.out.println("Found " + ratings.size() + " movies");
        for (Rating rating : ratings) {
            String id = rating.getItem();
            System.out.println(rating.getValue() + " Year: " + MovieDatabase.getYear(id) + " Time: " + MovieDatabase.getMinutes(id) + " " + MovieDatabase.getTitle(id));
        }
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings runner = new MovieRunnerSimilarRatings();
        
        
        //runner.printSimilarRatings();
        
        
        //runner.printSimilarRatingsByGenre();
        //runner.printSimilarRatingsByDirector();
        //runner.printSimilarRatingsByGenreAndMinutes();
        runner.printSimilarRatingsByYearAfterAndMinutes();
    }
}