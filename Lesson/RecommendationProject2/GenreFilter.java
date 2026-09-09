public class GenreFilter implements Filter {

    private String genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean satisfies(String id) {

        String genres = MovieDatabase.getGenres(id);

        String[] genreList = genres.split(",");

        for (String g : genreList) {

            if (g.trim().equals(genre)) {
                return true;
            }
        }

        return false;
    }
}