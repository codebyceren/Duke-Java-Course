package Lesson.ExportFinder;
import java.io.*;

public class ExportFinder {

    public static String[] splitCSV(String line) {

        String[] data = new String[3];
        String current = "";
        int index = 0;
        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {

            char c = line.charAt(i);

            if (c == '"') {
                insideQuotes = !insideQuotes;
            }
            else if (c == ',' && !insideQuotes) {
                data[index] = current;
                index++;
                current = "";
            }
            else {
                current += c;
            }
        }

        data[index] = current;

        return data;
    }


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
            new FileReader("Lesson/ExportFinder/exports.csv")
        );

        String line = br.readLine();

        int sugarCount = 0;

        String[] fishAndNutsCountries = new String[100];
        int fishAndNutsCount = 0;

        String[] trillionCountries = new String[100];
        String[] trillionValues = new String[100];
        int trillionCount = 0;


        while ((line = br.readLine()) != null) {

            String[] data = splitCSV(line);

            String country = data[0];
            String exports = data[1].toLowerCase();
            String value = data[2];


            // Fish AND Nuts

            if (exports.contains("fish") &&
                exports.contains("nuts")) {

                fishAndNutsCountries[fishAndNutsCount] = country;
                fishAndNutsCount++;
            }


            // Sugar

            if (exports.contains("sugar")) {
                sugarCount++;
            }


            // One trillion dollars or more

            if (value.length() > "$999,999,999,999".length()) {

                trillionCountries[trillionCount] = country;
                trillionValues[trillionCount] = value;
                trillionCount++;
            }
        }

        br.close();


        // Print Fish + Nuts

        System.out.println("Fish + Nuts:");

        for (int i = 0; i < fishAndNutsCount; i++) {

            System.out.println(
                (i + 1) + ". " +
                fishAndNutsCountries[i]
            );
        }


        // Print Trillion

        System.out.println();
        System.out.println("One trillion dollars or more:");

        for (int i = 0; i < trillionCount; i++) {

            System.out.println(
                (i + 1) + ". " +
                trillionCountries[i] +
                " - " +
                trillionValues[i]
            );
        }


        // Print Sugar

        System.out.println();
        System.out.println(
            "Countries exporting sugar: " +
            sugarCount
        );
    }
}