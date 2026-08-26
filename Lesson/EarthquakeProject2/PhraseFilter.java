public class PhraseFilter implements Filter {
    private String where;
    private String phrase;

    public PhraseFilter(String where, String phrase) {
        this.where = where;
        this.phrase = phrase;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();

        if (where.equals("start")) {
            return title.startsWith(phrase);
        } else if (where.equals("end")) {
            return title.endsWith(phrase);
        } else if (where.equals("any")) {
            return title.contains(phrase);
        }

        return false;
    }

    @Override
    public String getName() {
        return "Phrase";
    }
}