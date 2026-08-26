package Lesson.WebsiteVisits.websitevisits;

import java.util.ArrayList;
import java.util.HashMap;

public class CountTester {

    public static void main(String[] args) {

        LogAnalyzer la = new LogAnalyzer();

        // Read the log file
        la.readFile("Lesson/WebsiteVisits/data/weblog2-short_log");
        // 1. Test countVisitsPerIP()
        HashMap<String, Integer> counts = la.countVisitsPerIP();

        System.out.println("1. Visits per IP:");
        System.out.println(counts);

        // 2. Test mostNumberVisitsByIP()
        int mostVisits = la.mostNumberVisitsByIP(counts);

        System.out.println("\n2. Most number of visits by one IP:");
        System.out.println(mostVisits);

        // 3. Test iPsMostVisits()
        ArrayList<String> mostVisitedIPs = la.iPsMostVisits(counts);

        System.out.println("\n3. IPs with most visits:");
        System.out.println(mostVisitedIPs);

        // 4. Test iPsForDays()
        HashMap<String, ArrayList<String>> ipsForDays =
                la.iPsForDays();

        //System.out.println("\n4. IPs for each day:");
        System.out.println(ipsForDays);

        // 5. Test dayWithMostIPVisits()
        //String busiestDay = la.dayWithMostIPVisits(ipsForDays);

        //System.out.println("\n5. Day with most IP visits:");
        //System.out.println(busiestDay);

        // 6. Test iPsWithMostVisitsOnDay()
        String day = "Sep 24";

        ArrayList<String> ipsOnDay =
                la.iPsWithMostVisitsOnDay(ipsForDays, day);

        System.out.println("\n6. IPs with most visits on " + day + ":");
        System.out.println(ipsOnDay);

        // Count the number of unique IP addresses
        System.out.println("Unique IP count: " + counts.size());
    }
}