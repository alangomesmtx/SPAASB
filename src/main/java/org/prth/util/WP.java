package org.prth.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WP {
    // ANSI Colors
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";


    // Timestamp format
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static boolean showTimestamps = true;

    public static void setShowTimestamps(boolean show) {
        showTimestamps = show;
    }

    // Generic print method
    private static void print(String color, String label, String message, Integer width) {
        String time = showTimestamps ? "[" + LocalDateTime.now().format(FORMATTER) + "] " : "";
        if (width == null){
            System.out.println(time + color + " " + label + " " + message + RESET);
        }
        else{
            System.out.println(time + color + " " + label + " " + wrapText(message, width, label) + RESET);
        }
    }

    // Public methods
    public static void success(String message) {
        print(GREEN, "[SUCCESS]", message, null);
    }

    public static void warn(String message) {
        print(YELLOW, "[WARNING]", message, null);
    }

    public static void error(String message) {
        print(RED, "[ERROR]", message, null);
    }

    public static void info(String message) {
        print(BLUE, "[INFO]", message, null);
    }

    public static void debug(String message) {
        print(CYAN, "[DEBUG]", message, null);
    }

    // Public methods
    public static void success(String message, Integer width) {
        print(GREEN, "[SUCCESS]", message, width);
    }

    public static void warn(String message, Integer width) {
        print(YELLOW, "[WARNING]", message, width);
    }

    public static void error(String message, Integer width) {
        print(RED, "[ERROR]", message, width);
    }

    public static void info(String message, Integer width) {
        print(BLUE, "[INFO]", message, width);
    }

    public static void debug(String message, Integer width) {
        print(CYAN, "[DEBUG]", message, width);
    }

    // Word-wrap helper
    private static String wrapText(String text, Integer width, String label) {
        if (text.length() <= width) return text;
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < text.length()) {
            int end = Math.min(index + width, text.length());
            sb.append(text, index, end).append("\n" + " ".repeat(10 + label.length() + 3)); // indent after line break
            index = end;
        }
        return sb.toString();
    }
}
