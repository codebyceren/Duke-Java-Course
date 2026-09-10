import java.util.ArrayList;

public class RecommendationRunner implements Recommender {

    public ArrayList<String> getItemsToRate() {
        MovieDatabase.initialize("ratedmoviesfull.csv");

        ArrayList<String> movieIDs =
                MovieDatabase.filterBy(new TrueFilter());

        ArrayList<String> itemsToRate =
                new ArrayList<String>();

        int numberOfMovies =
                Math.min(15, movieIDs.size());

        for (int i = 0; i < numberOfMovies; i++) {
            itemsToRate.add(movieIDs.get(i));
        }

        return itemsToRate;
    }

    public void printRecommendationsFor(String webRaterID) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        FourthRatings fr = new FourthRatings();

        ArrayList<Rating> recommendations =
                fr.getSimilarRatings(webRaterID, 20, 3);

        if (recommendations.size() == 0) {
            System.out.println(
                "<p>No recommendations are available.</p>"
            );
            return;
        }

        System.out.println("<h2>Movie Recommendations</h2>");

        System.out.println(
            "<table style='border-collapse: collapse; width: 70%; font-family: Arial, sans-serif;'>"
        );

        System.out.println(
            "<tr style='background-color: #333; color: white;'>"
        );

        System.out.println(
            "<th style='border: 1px solid #999; padding: 12px; text-align: left;'>Movie</th>"
        );

        System.out.println(
            "<th style='border: 1px solid #999; padding: 12px; text-align: center;'>Rating</th>"
        );

        System.out.println("</tr>");

        int numberOfRecommendations =
                Math.min(20, recommendations.size());

        for (int i = 0; i < numberOfRecommendations; i++) {
            Rating rating = recommendations.get(i);

            String movieID = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);

            String rowColor =
                    (i % 2 == 0) ? "#f2f2f2" : "#ffffff";

            System.out.println(
                "<tr style='background-color: " + rowColor + ";'>"
            );

            System.out.println(
                "<td style='border: 1px solid #999; padding: 10px;'>" +
                movieTitle +
                "</td>"
            );

            System.out.println(
                "<td style='border: 1px solid #999; padding: 10px; text-align: center;'>" +
                String.format("%.2f", rating.getValue()) +
                "</td>"
            );

            System.out.println("</tr>");
        }

        System.out.println("</table>");
    }
}