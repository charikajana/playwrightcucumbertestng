package org.sabre.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AllureDirectorySetup {
    private static String allureBaseDir;
    private static String allureResultsDir;
    private static String allureReportDir;
    private static String dateDir;
    private static String timeDir;

    public static void setup() {
        deleteFolder(new File(System.getProperty("user.dir") + "/allure-results"));
        dateDir = getDateDir();
        timeDir = getTimeDir();
        allureBaseDir = "reports/" + dateDir + "/" + timeDir;
        allureResultsDir = allureBaseDir + "/allure-results";
        allureReportDir = allureBaseDir + "/allure-report";
        System.setProperty("allure.results.directory", allureResultsDir);
        System.setProperty("allure.report.directory", allureReportDir);
        // Create date and time directories if not present
        File dateDirFile = new File("reports/" + dateDir);
        if (!dateDirFile.exists()) dateDirFile.mkdirs();
        File timeDirFile = new File("reports/" + dateDir + "/" + timeDir);
        if (!timeDirFile.exists()) timeDirFile.mkdirs();
        // Create results and report directories if not present
        File resultsDir = new File(allureResultsDir);
        File reportDir = new File(allureReportDir);
        if (!resultsDir.exists()) resultsDir.mkdirs();
        if (!reportDir.exists()) reportDir.mkdirs();
        // Do NOT create environment.properties here; do it after copying results.
    }

    public static void copyResultsAndGenerateReport() {
        // Copy results from default location to dynamic directory
        File defaultResults = new File(System.getProperty("user.dir") + File.separator + "allure-results");
        File dynamicResults = new File(allureResultsDir);
        if (defaultResults.exists() && defaultResults.isDirectory()) {
            copyFolder(defaultResults, dynamicResults);
            deleteFolder(defaultResults);
        }
        // Create environment.properties in allure-results (after copy)
        File envFile = new File(allureResultsDir, "environment.properties");
        try (FileWriter writer = new FileWriter(envFile)) {
            writer.write("Browser=Chrome\n");
            writer.write("OS=" + System.getProperty("os.name") + "\n");
            writer.write("Env=QA\n");
            writer.write("ThreadCount=1\n");
        } catch (IOException e) {
            System.out.println("Failed to create environment.properties: " + e.getMessage());
        }
        if (defaultResults.exists() && defaultResults.isDirectory()) {
            copyFolder(defaultResults, dynamicResults);
            deleteFolder(defaultResults);
        }
        // Generate Allure report from dynamic directory using Allure CLI
        if (dynamicResults.exists() && dynamicResults.isDirectory() && dynamicResults.list().length > 0) {
            try {
                String allureCmd;
                if (System.getProperty("os.name").toLowerCase().contains("win")) {
                    // Try allure.bat, then allure.cmd, then allure
                    String[] winCmds = {"allure.bat", "allure.cmd", "allure"};
                    allureCmd = null;
                    for (String cmd : winCmds) {
                        try {
                            Process check = new ProcessBuilder(cmd, "--version").start();
                            if (check.waitFor() == 0) {
                                allureCmd = cmd;
                                break;
                            }
                        } catch (Exception ignored) {}
                    }
                    if (allureCmd == null) {
                        System.out.println("Allure CLI not found in PATH. Please ensure Allure CLI is installed and added to your PATH.");
                        return;
                    }
                } else {
                    allureCmd = "allure";
                }
                String[] command = {allureCmd, "generate", "allure-results", "-o", "allure-report", "--clean"};
                Process process = new ProcessBuilder(command)
                        .directory(new File(allureBaseDir))
                        .inheritIO()
                        .start();
                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    System.out.println("Allure report generation via Allure CLI failed with exit code: " + exitCode);
                } else {
                    System.out.println("Allure report generated at: " + allureReportDir);
                }
            } catch (IOException e) {
                System.out.println("Failed to generate Allure report via Allure CLI: Allure executable not found in PATH. Please ensure Allure CLI is installed and added to your PATH.");
            } catch (Exception e) {
                System.out.println("Failed to generate Allure report via Allure CLI: " + e.getMessage());
            }
        } else {
            System.out.println("No Allure results found to generate report.");
        }
    }

    private static void copyFolder(File src, File dest) {
        if (!dest.exists()) dest.mkdirs();
        for (File file : src.listFiles()) {
            File destFile = new File(dest, file.getName());
            if (file.isDirectory()) {
                copyFolder(file, destFile);
            } else {
                try (java.io.FileInputStream in = new java.io.FileInputStream(file);
                     java.io.FileOutputStream out = new java.io.FileOutputStream(destFile)) {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                } catch (IOException e) {
                    System.out.println("Failed to copy file: " + file.getAbsolutePath() + " to " + destFile.getAbsolutePath());
                }
            }
        }
    }

    private static void deleteFolder(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteFolder(file);
                }
            }
        }
        folder.delete();
    }

    private static String getDateDir() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMM");
        return sdf.format(new Date()).toUpperCase();
    }
    private static String getTimeDir() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(new Date());
    }

    public static String getAllureResultsDir() {
        return allureResultsDir;
    }
    public static String getAllureReportDir() {
        return allureReportDir;
    }
    public static String getDateDirValue() {
        return dateDir;
    }
    public static String getTimeDirValue() {
        return timeDir;
    }
}
