package Lesson.BabyNamesProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BabyNames {

    public void totalBirths(String fileName) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            int girls = 0;
            int boys = 0;
            int total = 0;

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts[1].equals("F")) {
                    girls++;
                } else {
                    boys++;
                }

                total++;
            }

            reader.close();

            System.out.println("Girls names: " + girls);
            System.out.println("Boys names: " + boys);
            System.out.println("Total names: " + total);

        } catch (IOException e) {
            System.out.println("File could not be read.");
        }
    }
    public int getRank(int year, String name, String gender) {

        String fileName = "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/BabyNamesProject/data/yob"
        + year + "short.csv";

        try {

            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;
            int rank = 0;
            while((line = reader.readLine()) != null) {
                String parts[] = line.split(",");
                
                if (parts[1].equals(gender)) {
                    rank++;

                    if(parts[0].equals(name)) {
                        reader.close();
                        return rank;
                    }
                }
                
            }
            reader.close();
            
            

        }catch (IOException e) {
            System.out.println("File could not be read.");
        }
        return -1;
        
    }

    public String getName(int year, int rank, String gender) {

        String fileName = "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/BabyNamesProject/data/yob"
        + year + "short.csv";

        try{
            BufferedReader reader = new BufferedReader( new FileReader(fileName));

            int currentRank = 0;

            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");

                if(parts[1].equals(gender)){
                    currentRank++;

                    if (currentRank == rank) {
                        reader.close();
                        return parts[0];
                    }
                }
            }
            reader.close();
            

        }catch(IOException e) {
            System.out.print("File could not be read.");
        }
        return "NO NAME";
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {

        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);

        System.out.println(rank);

        System.out.println(name + " born in " + year + " would be "
            + newName + " if she was born in " + newYear + ".");

        
    }

    public int yearOfHighestRank(String name, String gender) {

        int bestRank = -1;
        int bestYear = -1;

        for (int year = 2012; year <= 2014; year++) {

            int rank = getRank(year, name, gender);

            if (rank != -1 && (bestRank == -1 || rank < bestRank)) {
                bestRank = rank;
                bestYear = year;
            }
        }
        return bestYear;

    }

    public double getAverageRank(String name, String gender) {

        double totalRank = 0;
        int count = 0;

        for (int year = 2012; year <= 2014; year++) {

            int rank = getRank(year, name, gender);

            if (rank != -1) {
                totalRank += rank;
                count++;
            }

        }
        if (count == 0) {
        return -1.0;
        }

        return totalRank / count;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {

        String fileName = "/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/BabyNamesProject/data/yob"
        + year + "short.csv";

        int totalBirths = 0;

        try {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        
        boolean found = false;

        String line;
        while ((line = reader.readLine()) != null) {

            String[] parts = line.split(",");
            if (parts[1].equals(gender)) {

                if(parts[0].equals(name)){
                    found = true;
                }else if (!found) {
                    totalBirths += Integer.parseInt(parts[2]);
                }
                
            }
        }
        reader.close();

        } catch (IOException e) {
            System.out.println("File could not be read.");
        }
        return totalBirths;

    }

    public static void main(String[] args) {

        BabyNames babyNames = new BabyNames();


        //TOTAL BIRTHS
        babyNames.totalBirths("/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/BabyNamesProject/data/yob2012short.csv");
        babyNames.totalBirths("/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/BabyNamesProject/data/yob2013short.csv");
        babyNames.totalBirths("/Users/cerengunhan/Desktop/Duke-Java-Course/Lesson/BabyNamesProject/data/yob2014short.csv");

        //GET RANK
        System.out.println(babyNames.getRank(2012, "Mason", "M"));
        System.out.println(babyNames.getRank(2012, "Mason", "F"));

        //GET NAME
        System.out.println(babyNames.getName(2012, 2, "M"));
        System.out.println(babyNames.getName(2012, 3, "F"));
        System.out.println(babyNames.getName(2012, 10, "M"));

        //WHAT IS NAME IN YEAR
        babyNames.whatIsNameInYear("Isabella", 2012, 2014, "F");

        //YEAR OF HIGHEST RANK
        System.out.println(babyNames.yearOfHighestRank("Mason", "M"));

        //GET AVERANGE RANK
        System.out.println(babyNames.getAverageRank("Mason", "M"));
        System.out.println(babyNames.getAverageRank("Jacob", "M"));

        //GET TOTAL BIRTHS RANKED HIGER
        System.out.println(babyNames.getTotalBirthsRankedHigher(2012, "Ethan", "M"));
    }
}