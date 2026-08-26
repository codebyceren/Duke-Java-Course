package Lesson.WebsiteVisits.websitevisits;

public class LogEntry {
    private String ipAddress;
    private String date;
    private String request;
    private int statusCode;
    private int bytes;

    public LogEntry(String ipAddress, String date, String request,
                    int statusCode, int bytes) {
        this.ipAddress = ipAddress;
        this.date = date;
        this.request = request;
        this.statusCode = statusCode;
        this.bytes = bytes;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getDate() {
        return date;
    }

    public String getRequest() {
        return request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return ipAddress + " " + date + " " + request + " "
                + statusCode + " " + bytes;
    }
}