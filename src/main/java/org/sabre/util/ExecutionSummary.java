package org.sabre.util;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class ExecutionSummary {
    private static LocalDateTime startTime;
    private static LocalDateTime endTime;
    private static int totalTests = 0;
    private static int passed = 0;
    private static int failed = 0;
    private static String environment = "-";
    private static String browser = "-";
    private static final Set<String> tags = new HashSet<>();

    public static void markStart() {
        startTime = LocalDateTime.now();
    }

    public static void markEnd() {
        endTime = LocalDateTime.now();
    }

    public static void incrementTotal() {
        totalTests++;
    }

    public static void incrementPassed() {
        passed++;
    }

    public static void incrementFailed() {
        failed++;
    }

    public static int getTotalTests() {
        return totalTests;
    }

    public static int getPassed() {
        return passed;
    }

    public static int getFailed() {
        return failed;
    }

    public static String getStartTime() {
        return startTime != null ? startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "-";
    }

    public static String getEndTime() {
        return endTime != null ? endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "-";
    }

    public static String getDuration() {
        if (startTime == null || endTime == null) return "-";
        Duration duration = Duration.between(startTime, endTime);
        long minutes = duration.toMinutes();
        long seconds = duration.minusMinutes(minutes).getSeconds();
        return String.format("%d min %d sec", minutes, seconds);
    }

    public static String getPassPercentage() {
        if (totalTests == 0) return "0%";
        double percent = (passed * 100.0) / totalTests;
        return new DecimalFormat("#.##").format(percent) + "%";
    }

    public static String getFailPercentage() {
        if (totalTests == 0) return "0%";
        double percent = (failed * 100.0) / totalTests;
        return new DecimalFormat("#.##").format(percent) + "%";
    }

    public static void reset() {
        totalTests = 0;
        passed = 0;
        failed = 0;
        startTime = null;
        endTime = null;
    }

    public static void setEnvironment(String env) {
        environment = env;
    }

    public static String getEnvironment() {
        return environment;
    }

    public static void setBrowser(String browserName) {
        browser = browserName;
    }

    public static String getBrowser() {
        return browser;
    }

    public static void addTags(Set<String> scenarioTags) {
        tags.addAll(scenarioTags);
    }

    public static String getTags() {
        return tags.isEmpty() ? "-" : String.join(", ", tags);
    }
}
