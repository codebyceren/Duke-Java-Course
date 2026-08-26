package Lesson.CaesarCipherProject;

public class CaesarBreaker {

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

    public String decrypt(String encrypted) {

        int[] counts = countLetters(encrypted);

        int maxDex = maxIndex(counts);

        int dkey = maxDex - 4;

        if (dkey < 0) {
            dkey += 26;
        }

        CaesarCipher cc = new CaesarCipher(26 - dkey);

        return cc.encrypt(encrypted);
    }

    public void testDecrypt() {

        CaesarCipher cipher = new CaesarCipher(15);

        String encrypted = cipher.encrypt(
            "Hfs cpwewloj loks cd Hoto kyg Cyy."
        );

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypt(encrypted));
    }

    // Decrypt a message using two Caesar Cipher keys
    public String decryptTwoKeys(String encrypted, int key1, int key2) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < encrypted.length(); i++) {

            char ch = encrypted.charAt(i);

            if (Character.isLetter(ch)) {

                int key;

                if (i % 2 == 0) {
                    key = key1;
                } else {
                    key = key2;
                }

                CaesarCipher cc = new CaesarCipher(26 - key);

                String decryptedChar = cc.encrypt(String.valueOf(ch));

                result.append(decryptedChar);

            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        CaesarBreaker breaker = new CaesarBreaker();

        // Test the original Caesar breaker
        breaker.testDecrypt();

        // Test the two-key decryption
        String encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";

        String decrypted = breaker.decryptTwoKeys(encrypted, 14, 24);

        System.out.println("Two-key decrypted: " + decrypted);
    }
}