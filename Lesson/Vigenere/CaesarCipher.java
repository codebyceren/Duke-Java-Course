package Lesson.Vigenere;

public class CaesarCipher {

    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher(int key) {
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(ch));

            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);

                if (Character.isLowerCase(ch)) {
                    newChar = Character.toLowerCase(newChar);
                }

                encrypted.setCharAt(i, newChar);
            }
        }

        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipher inverse = new CaesarCipher(26 - mainKey);
        return inverse.encrypt(input);
    }

    public char encryptLetter(char c) {
        int idx = alphabet.indexOf(Character.toUpperCase(c));

        if (idx == -1) {
            return c;
        }

        char newChar = shiftedAlphabet.charAt(idx);

        if (Character.isLowerCase(c)) {
            newChar = Character.toLowerCase(newChar);
        }

        return newChar;
    }

    public char decryptLetter(char c) {
        CaesarCipher inverse = new CaesarCipher(26 - mainKey);
        return inverse.encryptLetter(c);
    }

    public int getKey() {
        return mainKey;
    }
}
