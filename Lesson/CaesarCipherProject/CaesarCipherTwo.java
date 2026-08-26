package Lesson.CaesarCipherProject;

public class CaesarCipherTwo {

    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwo(int key1, int key2) {

        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        mainKey1 = key1;
        mainKey2 = key2;

        shiftedAlphabet1 = alphabet.substring(key1)
                + alphabet.substring(0, key1);

        shiftedAlphabet2 = alphabet.substring(key2)
                + alphabet.substring(0, key2);
    }

    public String encrypt(String input) {

        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < encrypted.length(); i++) {

            char currChar = encrypted.charAt(i);

            int idx = alphabet.indexOf(Character.toUpperCase(currChar));

            if (idx != -1) {

                char newChar;

                if (i % 2 == 0) {
                    newChar = shiftedAlphabet1.charAt(idx);
                } else {
                    newChar = shiftedAlphabet2.charAt(idx);
                }

                if (Character.isLowerCase(currChar)) {
                    newChar = Character.toLowerCase(newChar);
                }

                encrypted.setCharAt(i, newChar);
            }
        }

        return encrypted.toString();
    }

    public String decrypt(String input) {

        StringBuilder decrypted = new StringBuilder(input);

        for (int i = 0; i < decrypted.length(); i++) {

            char currChar = decrypted.charAt(i);

            int idx = alphabet.indexOf(Character.toUpperCase(currChar));

            if (idx != -1) {

                char newChar;

                if (i % 2 == 0) {
                    newChar = alphabet.charAt(
                            (idx - mainKey1 + 26) % 26
                    );
                } else {
                    newChar = alphabet.charAt(
                            (idx - mainKey2 + 26) % 26
                    );
                }

                if (Character.isLowerCase(currChar)) {
                    newChar = Character.toLowerCase(newChar);
                }

                decrypted.setCharAt(i, newChar);
            }
        }

        return decrypted.toString();
    }
}