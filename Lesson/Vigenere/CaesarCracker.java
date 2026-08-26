package Lesson.Vigenere;

public class CaesarCracker {

    private char mostCommon;

    public CaesarCracker() {
        mostCommon = 'e';
    }

    public CaesarCracker(char c) {
        mostCommon = c;
    }

    public int getKey(String encrypted) {
        int[] freqs = countLetters(encrypted);

        int maxDex = maxIndex(freqs);

        int mostCommonPos = ((int) mostCommon) - ((int) 'a');

        int dkey = maxDex - mostCommonPos;

        if (maxDex < mostCommonPos) {
            dkey = 26 - (mostCommonPos - maxDex);
        }

        return dkey;
    }

    private int[] countLetters(String message) {
        int[] counts = new int[26];

        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));

            if (ch >= 'a' && ch <= 'z') {
                counts[ch - 'a']++;
            }
        }

        return counts;
    }

    private int maxIndex(int[] values) {
        int maxDex = 0;

        for (int i = 1; i < values.length; i++) {
            if (values[i] > values[maxDex]) {
                maxDex = i;
            }
        }

        return maxDex;
    }
}