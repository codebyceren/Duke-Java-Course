public class GenreFilter implements Filter {
    private String genre;

    public GenreFilter(String genre) {
        this.genre = genre.trim();
    }

    @Override
    public boolean satisfies(String id) {
        String genres = MovieDatabase.getGenres(id);
        if (genres == null) {
            return false;
        }
        String[] genreList = genres.split(",");
        for (String g : genreList) {
            if (g.trim().equalsIgnoreCase(genre)) {
                return true;
            }
        }
        return false;
    }
}