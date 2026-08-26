public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt " + index);
        }
        return myWords[index];
    }

    public int length() {
        return myWords.length;
    }

    public String toString() {
        String ret = "";

        for (String word : myWords) {
            ret += word + " ";
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || !(o instanceof WordGram)) {
            return false;
        }

        WordGram other = (WordGram) o;

        if (this.length() != other.length()) {
            return false;
        }

        for (int k = 0; k < myWords.length; k++) {
            if (!myWords[k].equals(other.myWords[k])) {
                return false;
            }
        }

        return true;
    }

    public WordGram shiftAdd(String word) {
        WordGram out = new WordGram(myWords, 0, myWords.length);

        for (int k = 0; k < myWords.length - 1; k++) {
            out.myWords[k] = myWords[k + 1];
        }

        out.myWords[myWords.length - 1] = word;

        return out;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}