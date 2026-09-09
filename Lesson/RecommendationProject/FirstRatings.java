import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename) throws IOException {

        ArrayList<Movie> movies = new ArrayList<Movie>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {

            String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            String id = fields[0];
            String title = fields[1];
            String year = fields[2];
            String country = fields[3];
            String genres = fields[4];
            String director = fields[5];

            int minutes = Integer.parseInt(fields[6].trim());
            String poster = fields[7];

            Movie movie = new Movie(
                id,
                title,
                year,
                genres,
                director,
                country,
                poster,
                minutes
            );

            movies.add(movie);
        }

        reader.close();

        return movies;
    }


    public void testLoadMovies() throws IOException {

        ArrayList<Movie> movies =
            loadMovies("Lesson/RecommendationProject/data/ratedmoviesfull.csv");

        System.out.println("Number of movies: " + movies.size());

        for (Movie movie : movies) {
            System.out.println(movie);
        }


        // Comedy movies

        int count = 0;

        for (Movie movie : movies) {
            if (movie.getGenres().contains("Comedy")) {
                count++;
            }
        }

        System.out.println(
            "Number of comedy movies: " + count
        );


        // Movies longer than 150 minutes

        int countMinutes = 0;

        for (Movie movie : movies) {
            if (movie.getMinutes() > 150) {
                countMinutes++;
            }
        }

        System.out.println(
            "Number of movies longer than 150 minutes: "
            + countMinutes
        );


        // Directors with the most movies

        HashMap<String, Integer> directorCounts =
            new HashMap<String, Integer>();

        for (Movie movie : movies) {

            String director = movie.getDirector();

            if (directorCounts.containsKey(director)) {

                directorCounts.put(
                    director,
                    directorCounts.get(director) + 1
                );

            } else {

                directorCounts.put(director, 1);
            }
        }


        int maxMovies = 0;

        for (int countDirector : directorCounts.values()) {

            if (countDirector > maxMovies) {
                maxMovies = countDirector;
            }
        }


        for (String directorKey : directorCounts.keySet()) {

            if (directorCounts.get(directorKey) == maxMovies) {

                System.out.println(
                    "Director with most movies: "
                    + directorKey
                    + " with "
                    + maxMovies
                    + " movies."
                );
            }
        }
    }


    public ArrayList<Rater> loadRaters(String filename)
        throws IOException {

        ArrayList<Rater> raters =
            new ArrayList<Rater>();

        BufferedReader reader =
            new BufferedReader(new FileReader(filename));

        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {

            String[] fields = line.split(",");

            String raterID = fields[0];
            String movieID = fields[1];
            double rating = Double.parseDouble(fields[2]);

            boolean found = false;

            for (Rater rater : raters) {

                if (rater.getID().equals(raterID)) {

                    rater.addRating(movieID, rating);
                    found = true;
                }
            }

            if (!found) {

                Rater newRater =
                    new Rater(raterID);

                newRater.addRating(movieID, rating);

                raters.add(newRater);
            }
        }

        reader.close();

        return raters;
    }


    public void testLoadRaters() throws IOException {

        ArrayList<Rater> raters =
            loadRaters("Lesson/RecommendationProject/data/ratings.csv");


        // Number of raters

        System.out.println(
            "Number of raters: " + raters.size()
        );


        // Each rater and their ratings

        for (Rater rater : raters) {

            System.out.println(
                "Rater ID: " + rater.getID()
                + " - Number of ratings: "
                + rater.numRatings()
            );

            for (String item : rater.getItemsRated()) {

                System.out.println(
                    "Movie ID: " + item
                    + " - Rating: "
                    + rater.getRating(item)
                );
            }
        }


        // Number of ratings by rater 2

        for (Rater rater : raters) {

            if (rater.getID().equals("193")) {

                System.out.println(
                    "Rater 193 has "
                    + rater.numRatings()
                    + " ratings."
                );
            }
        }


        // Maximum number of ratings by any rater

        int maxRatings = 0;

        for (Rater rater : raters) {

            if (rater.numRatings() > maxRatings) {

                maxRatings = rater.numRatings();
            }
        }

        System.out.println(
            "Maximum number of ratings: "
            + maxRatings
        );


        // Rater(s) with the maximum number of ratings

        for (Rater rater : raters) {

            if (rater.numRatings() == maxRatings) {

                System.out.println(
                    "Rater with most ratings: "
                    + rater.getID()
                );
            }
        }


        // Number of ratings for movie 1798709

        int movieRatingCount = 0;

        for (Rater rater : raters) {

            if (rater.hasRating("1798709")) {

                movieRatingCount++;
            }
        }

        System.out.println(
            "Number of ratings for movie 1798709: "
            + movieRatingCount
        );


        // Number of different movies rated by all raters

        ArrayList<String> moviesRated =
            new ArrayList<String>();

        for (Rater rater : raters) {

            for (String item : rater.getItemsRated()) {

                if (!moviesRated.contains(item)) {

                    moviesRated.add(item);
                }
            }
        }

        System.out.println(
            "Number of different movies rated: "
            + moviesRated.size()
        );
    }


    public static void main(String[] args) throws IOException {

        FirstRatings fr = new FirstRatings();

        //fr.testLoadMovies();
        fr.testLoadRaters();
    }
}