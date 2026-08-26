# Reading Web Logs

This project reads web server log files, parses each log entry, stores the entries in an ArrayList, and prints them.

## Program Flow

```text
                     Tester
                       │
                       │ creates
                       ↓
                 LogAnalyzer
                       │
                       │ readFile()
                       ↓
                 short-test_log
                       │
                       │ each line
                       ↓
                  WebLogParser
                       │
                       │ parseEntry()
                       ↓
                   LogEntry
                       │
                       │ add()
                       ↓
                ArrayList<LogEntry>
                       │
                       │ printAll()
                       ↓
                    Terminal