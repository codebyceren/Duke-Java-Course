package Lesson.LogAnalyzerProject;

public class Tester {

    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();

        la.readFile("Lesson/LogAnalyzerProject/short-test_log");

        System.out.println("Number of unique IP addresses: "
                + la.countUniqueIPs());
    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();

        la.readFile("Lesson/LogAnalyzerProject/short-test_log");

        System.out.println("Log entries with status code higher than 300:");

        la.printAllHigherThanNum(300);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();

        la.readFile("Lesson/LogAnalyzerProject/weblog-short_log");

        System.out.println("Unique IPs on Sep 14: "
                + la.uniqueIPVisitsOnDay("Sep 14"));

        System.out.println("Unique IPs on Sep 30: "
                + la.uniqueIPVisitsOnDay("Sep 30"));
    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();

        la.readFile("Lesson/LogAnalyzerProject/short-test_log");

        System.out.println("Unique IPs with status codes 200-299: "
                + la.countUniqueIPsInRange(200, 299));

        System.out.println("Unique IPs with status codes 300-399: "
                + la.countUniqueIPsInRange(300, 399));
    }
    public void testUniqueIPVisitsOnDayWeblog1() {
        LogAnalyzer la = new LogAnalyzer();

        la.readFile("Lesson/LogAnalyzerProject/weblog1_log");

        System.out.println(
            la.uniqueIPVisitsOnDay("Mar 24")
        );
        }

    public void testCountUniqueIPsInRangeWeblog1() {
        LogAnalyzer la = new LogAnalyzer();

        la.readFile("Lesson/LogAnalyzerProject/weblog1_log");

        System.out.println(
            la.countUniqueIPsInRange(300, 399)
        );
    }


    public static void main(String[] args) {
        Tester tester = new Tester();

        tester.testUniqueIP();
        tester.testPrintAllHigherThanNum();
        tester.testUniqueIPVisitsOnDay();
        tester.testCountUniqueIPsInRange();

        LogAnalyzer la = new LogAnalyzer();

        la.readFile("Lesson/LogAnalyzerProject/weblog1_log");

        la.printAllHigherThanNum(400);

        tester.testUniqueIPVisitsOnDayWeblog1();
        tester.testCountUniqueIPsInRangeWeblog1();

        
    }
}