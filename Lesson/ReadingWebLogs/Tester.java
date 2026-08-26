package Lesson.ReadingWebLogs;

public class Tester {

    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();

        la.readFile("Lesson/ReadingWebLogs/short-test_log");

        la.printAll();
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        t.testLogAnalyzer();
    }
}