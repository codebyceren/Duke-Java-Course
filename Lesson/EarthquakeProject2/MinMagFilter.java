public class MinMagFilter implements Filter {
    private double magMin;

    public MinMagFilter(double magMin) {
        this.magMin = magMin;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin;
    }

    @Override
    public String getName() {
        return "MinMag";
    }
}