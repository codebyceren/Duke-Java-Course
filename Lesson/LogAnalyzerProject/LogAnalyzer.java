package Lesson.LogAnalyzerProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                LogEntry le = WebLogParser.parseEntry(line);
                records.add(le);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();

        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();

            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }

        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();

        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();

            if (date.contains(someday)) {
                String ipAddr = le.getIpAddress();

                if (!uniqueIPs.contains(ipAddr)) {
                    uniqueIPs.add(ipAddr);
                }
            }
        }

        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();

        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();

            if (statusCode >= low && statusCode <= high) {
                String ipAddr = le.getIpAddress();

                if (!uniqueIPs.contains(ipAddr)) {
                    uniqueIPs.add(ipAddr);
                }
            }
        }

        return uniqueIPs.size();
    }
}