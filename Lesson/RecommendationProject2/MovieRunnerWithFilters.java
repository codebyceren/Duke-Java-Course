import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

    public void printAverageRatings() {

        ThirdRatings tr =
            new ThirdRatings("ratings.csv");

        MovieDatabase.initialize("ratedmoviesfull.csv");

        System.out.println(
            "read data for " +
            tr.getRaterSize() +
            " raters"
        );

        System.out.println(
            "read data for " +
            MovieDatabase.size() +
            " movies"
        );

        ArrayList<Rating> ratings =
            tr.getAverageRatings(35);

        System.out.println(
            "found " +
            ratings.size() +
            " movies"
        );

        Collections.sort(ratings);

        for (Rating rating : ratings) {

            System.out.println(
                rating.getValue() +
                " " +
                MovieDatabase.getTitle(
                    rating.getItem()
                )
            );
        }
    }

    public void printAverageRatingsByYear() {

        ThirdRatings tr =
            new ThirdRatings("ratings.csv");

        MovieDatabase.initialize("ratedmoviesfull.csv");

        YearsAfterFilter filter =
            new YearsAfterFilter(2000);

        ArrayList<Rating> ratings =
            tr.getAverageRatingsByFilter(20, filter);

        System.out.println(
            "found " +
            ratings.size() +
            " movies"
        );

        Collections.sort(ratings);

        for (Rating rating : ratings) {

            String id = rating.getItem();

            System.out.println(
                rating.getValue() +
                " " +
                MovieDatabase.getYear(id) +
                " " +
                MovieDatabase.getTitle(id)
            );
        }
    }

    public void printAverageRatingsByGenre() {

        ThirdRatings tr =
            new ThirdRatings("ratings.csv");

        MovieDatabase.initialize("ratedmoviesfull.csv");

        GenreFilter filter =
            new GenreFilter("Crime");

        ArrayList<Rating> ratings =
            tr.getAverageRatingsByFilter(20, filter);

        System.out.println(
            "found " +
            ratings.size() +
            " movies"
        );

        Collections.sort(ratings);

        for (Rating rating : ratings) {

            String id = rating.getItem();

            System.out.println(
                rating.getValue() +
                " " +
                MovieDatabase.getTitle(id)
            );

            System.out.println(
                "    " +
                MovieDatabase.getGenres(id)
            );
        }
    }

    public void printAverageRatingsByMinutes() {

        ThirdRatings tr =
            new ThirdRatings("ratings.csv");

        MovieDatabase.initialize("ratedmoviesfull.csv");

        MinutesFilter filter =
            new MinutesFilter(105, 135);

        ArrayList<Rating> ratings =
            tr.getAverageRatingsByFilter(5, filter);

        System.out.println(
            "found " +
            ratings.size() +
            " movies"
        );

        Collections.sort(ratings);

        for (Rating rating : ratings) {

            String id = rating.getItem();

            System.out.println(
                rating.getValue() +
                " Time: " +
                MovieDatabase.getMinutes(id) +
                " " +
                MovieDatabase.getTitle(id)
            );
        }
    }

    public void printAverageRatingsByDirectors() {

        ThirdRatings tr =
            new ThirdRatings("ratings.csv");

        MovieDatabase.initialize("ratedmoviesfull.csv");

        DirectorsFilter filter =
            new DirectorsFilter(
                "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"
            );

        ArrayList<Rating> ratings =
            tr.getAverageRatingsByFilter(4, filter);

        System.out.println(
            "found " +
            ratings.size() +
            " movies"
        );

        Collections.sort(ratings);

        for (Rating rating : ratings) {

            String id = rating.getItem();

            System.out.println(
                rating.getValue() +
                " " +
                MovieDatabase.getTitle(id)
            );

            System.out.println(
                "    " +
                MovieDatabase.getDirector(id)
            );
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {

        ThirdRatings tr =
            new ThirdRatings("ratings.csv");

        MovieDatabase.initialize("ratedmoviesfull.csv");

        AllFilters filters =
            new AllFilters();

        filters.addFilter(
            new YearsAfterFilter(1990)
        );

        filters.addFilter(
            new GenreFilter("Drama")
        );

        ArrayList<Rating> ratings =
            tr.getAverageRatingsByFilter(8, filters);

        System.out.println(
            "found " +
            ratings.size() +
            " movies"
        );

        Collections.sort(ratings);

        for (Rating rating : ratings) {

            String id = rating.getItem();

            System.out.println(
                rating.getValue() +
                " " +
                MovieDatabase.getYear(id) +
                " " +
                MovieDatabase.getTitle(id)
            );

            System.out.println(
                "    " +
                MovieDatabase.getGenres(id)
            );
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes() {

        ThirdRatings tr =
            new ThirdRatings("ratings.csv");

        MovieDatabase.initialize("ratedmoviesfull.csv");

        AllFilters filters =
            new AllFilters();

        filters.addFilter(
            new MinutesFilter(90, 180)
        );

        filters.addFilter(
            new DirectorsFilter(
                "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"
            )
        );

        ArrayList<Rating> ratings =
            tr.getAverageRatingsByFilter(3, filters);

        System.out.println(
            "found " +
            ratings.size() +
            " movies"
        );

        Collections.sort(ratings);

        for (Rating rating : ratings) {

            String id = rating.getItem();

            System.out.println(
                rating.getValue() +
                " Time: " +
                MovieDatabase.getMinutes(id) +
                " " +
                MovieDatabase.getTitle(id)
            );

            System.out.println(
                "    " +
                MovieDatabase.getDirector(id)
            );
        }
    }

    public static void main(String[] args) {

        MovieRunnerWithFilters runner =
            new MovieRunnerWithFilters();

        //runner.printAverageRatings();

        //runner.printAverageRatingsByYear();
        //runner.printAverageRatingsByGenre();
        //runner.printAverageRatingsByMinutes();
        runner.printAverageRatingsByDirectors();
        //runner.printAverageRatingsByYearAfterAndGenre();
        //runner.printAverageRatingsByDirectorsAndMinutes();
    }
}