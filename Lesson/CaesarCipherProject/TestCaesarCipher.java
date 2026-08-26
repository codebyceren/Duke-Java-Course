package Lesson.CaesarCipherProject;

public class TestCaesarCipher {

    public int[] countLetters(String message) {

        int[] counts = new int[26];

        for (int k = 0; k < message.length(); k++) {

            char ch = Character.toLowerCase(message.charAt(k));

            if (ch >= 'a' && ch <= 'z') {
                int index = ch - 'a';
                counts[index]++;
            }
        }

        return counts;
    }

    public int maxIndex(int[] values) {

        int maxIndex = 0;

        for (int k = 0; k < values.length; k++) {

            if (values[k] > values[maxIndex]) {
                maxIndex = k;
            }
        }

        return maxIndex;
    }

    public void simpleTests() {

        String message = "This is a secret message with many words.";

        CaesarCipher cc = new CaesarCipher(18);

        String encrypted = cc.encrypt(message);

        System.out.println("Encrypted:");
        System.out.println(encrypted);

        String decrypted = cc.decrypt(encrypted);

        System.out.println("Decrypted:");
        System.out.println(decrypted);

        String broken = breakCaesarCipher(encrypted);

        System.out.println("Broken:");
        System.out.println(broken);
    }

    public String breakCaesarCipher(String input) {

        int[] counts = countLetters(input);

        int maxDex = maxIndex(counts);

        int dkey = maxDex - 4;

        if (dkey < 0) {
            dkey += 26;
        }

        CaesarCipher cc = new CaesarCipher(26 - dkey);

        return cc.encrypt(input);
    }

    public static void main(String[] args) {

        TestCaesarCipher tester = new TestCaesarCipher();

        tester.simpleTests();
    }
}