package Lesson.Vigenere;

public class VigenereCipher {

    private CaesarCipher[] ciphers;

    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];

        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetter(ch)) {
                int keyIndex = i % ciphers.length;
                encrypted.setCharAt(i, ciphers[keyIndex].encryptLetter(ch));
            }
        }

        return encrypted.toString();
    }

    public String decrypt(String input) {
        StringBuilder decrypted = new StringBuilder(input);

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetter(ch)) {
                int keyIndex = i % ciphers.length;
                decrypted.setCharAt(i, ciphers[keyIndex].decryptLetter(ch));
            }
        }

        return decrypted.toString();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (CaesarCipher cipher : ciphers) {
            result.append((char) ('a' + cipher.getKey()));
        }

        return result.toString();
    }
}