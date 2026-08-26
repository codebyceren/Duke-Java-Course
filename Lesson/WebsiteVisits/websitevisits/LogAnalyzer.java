package Lesson.WebsiteVisits.websitevisits;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LogAnalyzer {

    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (!line.trim().isEmpty()) {
                    LogEntry entry = WebLogParser.parseEntry(line);
                    records.add(entry);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    // 1. Count the number of visits for each IP address
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();

        for (LogEntry le : records) {
            String ip = le.getIpAddress();

            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }

        return counts;
    }

    // 2. Find the maximum number of visits by a single IP address
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int max = 0;

        for (String ip : counts.keySet()) {
            int visits = counts.get(ip);

            if (visits > max) {
                max = visits;
            }
        }

        return max;
    }

    // 3. Find all IP addresses with the maximum number of visits
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> result = new ArrayList<String>();

        int max = mostNumberVisitsByIP(counts);

        for (String ip : counts.keySet()) {
            if (counts.get(ip) == max) {
                result.add(ip);
            }
        }

        return result;
    }

    // 4. Map each day to the list of IP addresses that visited that day
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> result =
                new HashMap<String, ArrayList<String>>();

        for (LogEntry le : records) {
            String date = le.getDate();

            // The date is stored as "Wed Sep 30"
            // We need "Sep 30"
            String[] parts = date.split(" ");
            String day = parts[1] + " " + parts[2];

            if (!result.containsKey(day)) {
                result.put(day, new ArrayList<String>());
            }

            result.get(day).add(le.getIpAddress());
        }

        return result;
    }

    // 5. Find the day with the most IP visits
    public String dayWithMostIPVisits(
            HashMap<String, ArrayList<String>> iPsForDays) {

        String maxDay = null;
        int maxVisits = 0;

        for (String day : iPsForDays.keySet()) {
            int visits = iPsForDays.get(day).size();

            if (visits > maxVisits) {
                maxVisits = visits;
                maxDay = day;
            }
        }

        return maxDay;
    }

    // 6. Find the IP addresses with the most visits on a given day
    public ArrayList<String> iPsWithMostVisitsOnDay(
            HashMap<String, ArrayList<String>> iPsForDays,
            String day) {

        ArrayList<String> ips = iPsForDays.get(day);

        HashMap<String, Integer> counts = new HashMap<String, Integer>();

        for (String ip : ips) {
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }

        return iPsMostVisits(counts);
    }
}