package Lesson.WeatherProject;

import java.io.File;
import java.util.Scanner;
public class Weather {

    // 1. Coldest temperature in one file

    public static void coldestHourInFile(String fileName) {

        try {
            File file = new File(
                "Lesson/WeatherProject/Weather-2014/" + fileName
            );

            Scanner input = new Scanner(file);
            input.nextLine();

            double coldest = 9999;
            String time = "";

            while (input.hasNextLine()) {

                String[] data = input.nextLine().split(",");

                double temperature = Double.parseDouble(data[1]);

                if (temperature != -9999 && temperature < coldest) {
                    coldest = temperature;
                    time = data[13];
                }
            }

            input.close();

            System.out.println(
                "Coldest temperature was " + coldest +
                " at " + time
            );

        } catch (Exception e) {
            System.out.println("Could not read file.");
        }
    }


    // 2. File with the coldest temperature

    public static void fileWithColdestTemperature() {

        File folder = new File(
            "Lesson/WeatherProject/Weather-2014"
        );

        double coldest = 9999;
        String coldestFile = "";

        for (File file : folder.listFiles()) {

            if (!file.getName().endsWith(".csv")) {
                continue;
            }

            try {
                Scanner input = new Scanner(file);
                input.nextLine();

                while (input.hasNextLine()) {

                    String[] data =
                        input.nextLine().split(",");

                    double temperature =
                        Double.parseDouble(data[1]);

                    if (temperature != -9999 &&
                        temperature < coldest) {

                        coldest = temperature;
                        coldestFile = file.getName();
                    }
                }

                input.close();

            } catch (Exception e) {
                System.out.println("Could not read file.");
            }
        }

        System.out.println(
            "Coldest temperature was " + coldest
        );

        System.out.println(
            "Coldest day was in file " + coldestFile
        );
    }


    // 3. Lowest humidity in one file

    public static void lowestHumidityInFile(String fileName) {

        try {
            File file = new File(
                "Lesson/WeatherProject/Weather-2014/" + fileName
            );

            Scanner input = new Scanner(file);
            input.nextLine();

            double lowest = 9999;
            String time = "";

            while (input.hasNextLine()) {

                String[] data =
                    input.nextLine().split(",");

                if (data[3].equals("N/A")) {
                    continue;
                }

                double humidity =
                    Double.parseDouble(data[3]);

                if (humidity < lowest) {
                    lowest = humidity;
                    time = data[13];
                }
            }

            input.close();

            System.out.println(
                "Lowest Humidity was " + lowest +
                " at " + time
            );

        } catch (Exception e) {
            System.out.println("Could not read file.");
        }
    }


    // 4. Lowest humidity in all 2014 files

    public static void lowestHumidityInManyFiles() {

        File folder = new File(
            "Lesson/WeatherProject/Weather-2014"
        );

        double lowest = 9999;
        String time = "";

        for (File file : folder.listFiles()) {

            if (!file.getName().endsWith(".csv")) {
                continue;
            }

            try {
                Scanner input = new Scanner(file);
                input.nextLine();

                while (input.hasNextLine()) {

                    String[] data =
                        input.nextLine().split(",");

                    if (data[3].equals("N/A")) {
                        continue;
                    }

                    double humidity =
                        Double.parseDouble(data[3]);

                    if (humidity < lowest) {
                        lowest = humidity;
                        time = data[13];
                    }
                }

                input.close();

            } catch (Exception e) {
                System.out.println("Could not read file.");
            }
        }

        System.out.println(
            "Lowest Humidity was " + lowest +
            " at " + time
        );
    }


    // 5. Average temperature in one file

    public static void averageTemperatureInFile(String fileName) {

        try {
            File file = new File(
                "Lesson/WeatherProject/Weather-2014/" + fileName
            );

            Scanner input = new Scanner(file);
            input.nextLine();

            double total = 0;
            int count = 0;

            while (input.hasNextLine()) {

                String[] data =
                    input.nextLine().split(",");

                double temperature =
                    Double.parseDouble(data[1]);

                if (temperature != -9999) {
                    total += temperature;
                    count++;
                }
            }

            input.close();

            double average = total / count;

            System.out.println(
                "Average temperature in file is " + average
            );

        } catch (Exception e) {
            System.out.println("Could not read file.");
        }
    }


    // 6. Average temperature with high humidity

    public static void averageTemperatureWithHighHumidityInFile(
            String fileName, int value) {

        try {
            File file = new File(
                "Lesson/WeatherProject/Weather-2014/" + fileName
            );

            Scanner input = new Scanner(file);
            input.nextLine();

            double total = 0;
            int count = 0;

            while (input.hasNextLine()) {

                String[] data =
                    input.nextLine().split(",");

                if (data[3].equals("N/A")) {
                    continue;
                }

                double humidity =
                    Double.parseDouble(data[3]);

                double temperature =
                    Double.parseDouble(data[1]);

                if (temperature != -9999 &&
                    humidity >= value) {

                    total += temperature;
                    count++;
                }
            }

            input.close();

            if (count == 0) {

                System.out.println(
                    "No temperatures with that humidity"
                );

            } else {

                double average = total / count;

                System.out.println(
                    "Average Temp when high Humidity is "
                    + average
                );
            }

        } catch (Exception e) {
            System.out.println("Could not read file.");
        }
    }


    public static void main(String[] args) {
        
        coldestHourInFile("weather-2014-05-01.csv");

        fileWithColdestTemperature();

        lowestHumidityInManyFiles();

        averageTemperatureInFile("weather-2014-06-01.csv");

        averageTemperatureWithHighHumidityInFile(
        "weather-2014-03-30.csv", 80
        );
    }
}