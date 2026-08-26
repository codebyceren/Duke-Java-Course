package Lesson.FirstCSV;
import java.io.*;

public class FirstCSVExample {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
            new FileReader("Lesson/FirstCSV/food.csv")
        );

        String line = br.readLine();

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            String name = data[0];
            String favoriteFood = data[1];
            String favoriteColor = data[2];

            System.out.println(
                name + "'s favorite food is " +
                favoriteFood +
                " and favorite color is " +
                favoriteColor
            );
        }

        br.close();
    }
}