package Lesson.WebsiteVisits.websitevisits;

public class WebLogParser {

    public static LogEntry parseEntry(String entry) {
        String[] parts = entry.split(" ");

        String ipAddress = parts[0];
        String date = parts[1];

        int statusIndex = parts.length - 2;
        int bytesIndex = parts.length - 1;

        StringBuilder request = new StringBuilder();

        for (int i = 2; i < statusIndex; i++) {
            if (i > 2) {
                request.append(" ");
            }
            request.append(parts[i]);
        }

        int statusCode = Integer.parseInt(parts[statusIndex]);
        int bytes = Integer.parseInt(parts[bytesIndex]);

        return new LogEntry(
                ipAddress,
                date,
                request.toString(),
                statusCode,
                bytes
        );
    }
}