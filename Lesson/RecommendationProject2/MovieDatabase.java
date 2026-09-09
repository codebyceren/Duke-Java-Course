import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MovieDatabase {

    private static HashMap<String, Movie> ourMovies;

    public static void initialize(String moviefile) {

        if (ourMovies == null) {

            ourMovies =
                new HashMap<String, Movie>();

            loadMovies(
                "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/RecommendationProject2/data/"
                + moviefile
            );
        }
    }

    private static void initialize() {

        if (ourMovies == null) {

            ourMovies =
                new HashMap<String, Movie>();

            loadMovies(
                "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/RecommendationProject2/data/ratedmoviesfull.csv"
            );
        }
    }

    private static void loadMovies(String filename) {

        try {

            BufferedReader br =
                new BufferedReader(
                    new FileReader(filename)
                );

            String line = br.readLine();

            while ((line = br.readLine()) != null) {

                ArrayList<String> fields =
                    parseCSVLine(line);

                if (fields.size() >= 8) {

                    String id = fields.get(0);
                    String title = fields.get(1);
                    String year = fields.get(2);
                    String country = fields.get(3);
                    String genres = fields.get(4);
                    String director = fields.get(5);
                    String minutes = fields.get(6);
                    String poster = fields.get(7);

                    Movie movie =
                        new Movie(
                            id,
                            title,
                            year,
                            genres,
                            director,
                            country,
                            poster,
                            Integer.parseInt(minutes)
                        );

                    ourMovies.put(id, movie);
                }
            }

            br.close();

        } catch (IOException e) {

            System.out.println(
                "Error reading file: "
                + filename
            );

            e.printStackTrace();
        }
    }

    private static ArrayList<String> parseCSVLine(
        String line
    ) {

        ArrayList<String> fields =
            new ArrayList<String>();

        boolean insideQuotes = false;

        StringBuilder current =
            new StringBuilder();

        for (int i = 0; i < line.length(); i++) {

            char c = line.charAt(i);

            if (c == '"') {

                insideQuotes = !insideQuotes;

            } else if (
                c == ',' && !insideQuotes
            ) {

                fields.add(
                    current.toString().trim()
                );

                current.setLength(0);

            } else {

                current.append(c);
            }
        }

        fields.add(
            current.toString().trim()
        );

        return fields;
    }

    public static boolean containsID(String id) {

        initialize();

        return ourMovies.containsKey(id);
    }

    public static int getYear(String id) {

        initialize();

        return ourMovies.get(id).getYear();
    }

    public static String getGenres(String id) {

        initialize();

        return ourMovies.get(id).getGenres();
    }

    public static String getTitle(String id) {

        initialize();

        return ourMovies.get(id).getTitle();
    }

    public static Movie getMovie(String id) {

        initialize();

        return ourMovies.get(id);
    }

    public static String getPoster(String id) {

        initialize();

        return ourMovies.get(id).getPoster();
    }

    public static int getMinutes(String id) {

        initialize();

        return ourMovies.get(id).getMinutes();
    }

    public static String getCountry(String id) {

        initialize();

        return ourMovies.get(id).getCountry();
    }

    public static String getDirector(String id) {

        initialize();

        return ourMovies.get(id).getDirector();
    }

    public static int size() {

        initialize();

        return ourMovies.size();
    }

    public static ArrayList<String> filterBy(
        Filter f
    ) {

        initialize();

        ArrayList<String> list =
            new ArrayList<String>();

        for (String id : ourMovies.keySet()) {

            if (f.satisfies(id)) {
                list.add(id);
            }
        }

        return list;
    }
}