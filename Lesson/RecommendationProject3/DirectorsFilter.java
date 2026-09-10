public class DirectorsFilter implements Filter {
    private String[] directors;

    public DirectorsFilter(String directors) {
        this.directors = directors.split(",");
    }

    @Override
    public boolean satisfies(String id) {
        String movieDirectors = MovieDatabase.getDirector(id);
        if (movieDirectors == null) {
            return false;
        }
        String[] movieDirectorList = movieDirectors.split(",");
        for (String wantedDirector : directors) {
            wantedDirector = wantedDirector.trim();
            for (String movieDirector : movieDirectorList) {
                if (movieDirector.trim().equalsIgnoreCase(wantedDirector)) {
                    return true;
                }
            }
        }
        return false;
    }
}