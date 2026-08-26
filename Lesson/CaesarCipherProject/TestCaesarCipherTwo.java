package Lesson.CaesarCipherProject;

public class TestCaesarCipherTwo {

    public String halfOfString(String message, int start) {

        StringBuilder result = new StringBuilder();

        for (int i = start; i < message.length(); i += 2) {
            result.append(message.charAt(i));
        }

        return result.toString();
    }

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

        String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";

        CaesarCipherTwo cc = new CaesarCipherTwo(14, 24);

        

        System.out.println(cc.encrypt(message));
        String encrypted = cc.encrypt(message);

        System.out.println("Encrypted:");
        System.out.println(encrypted);

        String decrypted = cc.decrypt(encrypted);

        System.out.println("Decrypted:");
        System.out.println(decrypted);

        String broken = breakCaesarCipher(encrypted);

        System.out.println("Broken:");
        System.out.println(broken);

        String question6 = "Hfs cpwewloj loks cd Hoto kyg Cyy.";

        System.out.println("Question 6:");
        System.out.println(cc.decrypt(question6));
    }

    public String breakCaesarCipher(String input) {

        String half1 = halfOfString(input, 0);
        String half2 = halfOfString(input, 1);

        int[] counts1 = countLetters(half1);
        int[] counts2 = countLetters(half2);

        int maxDex1 = maxIndex(counts1);
        int maxDex2 = maxIndex(counts2);

        int key1 = maxDex1 - 4;
        int key2 = maxDex2 - 4;

        if (key1 < 0) {
            key1 += 26;
        }

        if (key2 < 0) {
            key2 += 26;
        }

        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);

        return cc.decrypt(input);
    }

    public static void main(String[] args) {

        TestCaesarCipherTwo tester = new TestCaesarCipherTwo();

        tester.simpleTests();
    }
}