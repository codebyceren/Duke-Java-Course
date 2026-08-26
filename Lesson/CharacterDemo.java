package Lesson;

public class CharacterDemo {
    public void digitTest() {
        String test = "ABCabc123!@#";
        for (int i = 0; i < test.length(); i++) {
            char ch = test.charAt(i);
            if (Character.isDigit(ch)) {
                System.out.println(ch + " is a digit");
            } else if (Character.isAlphabetic(ch)) {
                System.out.println(ch + " is alphabetic");
            } else if (ch == '#') {
                System.out.println(ch + " is a hashtag");
            }
        }
    }

    public void conversionTest() {
        String test = "ABCabc123!@#";
        for (int i = 0; i < test.length(); i++) {
            char ch = test.charAt(i);
            char uch = Character.toUpperCase(ch);
            char lch = Character.toLowerCase(ch);
            System.out.println(ch + " -> Uppercase: " + uch + ", Lowercase: " + lch);
        }
    }

    public static void main(String[] args) {
        CharacterDemo demo = new CharacterDemo();
        demo.digitTest();
        demo.conversionTest();
    }
}
