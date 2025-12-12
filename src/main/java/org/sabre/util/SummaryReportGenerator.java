package org.sabre.util;

public class SummaryReportGenerator {
    public static String generateHtmlSummary(String allureReportLink) {
        return "<html>" +
                "<body>" +
                "<h2>Automation Execution Summary</h2>" +
                "<table border='1' cellpadding='5' cellspacing='0'>" +
                "<tr><th>Execution Start</th><td>" + ExecutionSummary.getStartTime() + "</td></tr>" +
                "<tr><th>Execution End</th><td>" + ExecutionSummary.getEndTime() + "</td></tr>" +
                "<tr><th>Duration</th><td>" + ExecutionSummary.getDuration() + "</td></tr>" +
                "<tr><th>Total Test Cases</th><td>" + ExecutionSummary.getTotalTests() + "</td></tr>" +
                "<tr><th>Passed</th><td>" + ExecutionSummary.getPassed() + "</td></tr>" +
                "<tr><th>Failed</th><td>" + ExecutionSummary.getFailed() + "</td></tr>" +
                "<tr><th>Pass %</th><td>" + ExecutionSummary.getPassPercentage() + "</td></tr>" +
                "<tr><th>Fail %</th><td>" + ExecutionSummary.getFailPercentage() + "</td></tr>" +
                "<tr><th>Allure Report</th><td><a href='" + allureReportLink + "'>View Report</a></td></tr>" +
                "</table>" +
                "</body>" +
                "</html>";
    }
}
