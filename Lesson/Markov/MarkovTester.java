public class MarkovTester {

    public static void main(String[] args) {

        String text =
            "this is a test yes this is really a test yes a test this is wow";

        EfficientMarkovWord model =
            new EfficientMarkovWord(2);

        model.setRandom(42);
        model.setTraining(text);

        model.printHashMapInfo();

        String[] words = text.split("\\s+");

        WordGram thisIs = new WordGram(words, 0, 2);
        WordGram aTest = new WordGram(words, 2, 2);
        WordGram isWow = new WordGram(words, words.length - 2, 2);

        System.out.println();
        System.out.println("Follows of 'this is':");
        System.out.println(model.getFollows(thisIs));

        System.out.println();
        System.out.println("Follows of 'a test':");
        System.out.println(model.getFollows(aTest));

        System.out.println();
        System.out.println("Follows of 'is wow':");
        System.out.println(model.getFollows(isWow));

        System.out.println();
        System.out.println("Generated text:");
        System.out.println(model.getRandomText(50));
    }
}