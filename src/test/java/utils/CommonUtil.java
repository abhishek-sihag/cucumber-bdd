package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;

public class CommonUtil {

    static final Logger LOG = LogManager.getLogger(CommonUtil.class);

    public static String formatTable(List<Map<String,String>> rows){
        if(rows==null || rows.isEmpty()){
            LOG.warn("No data available.");
            return "";
        }
        Set<String> headerSet = new HashSet<>();
        rows.forEach(s -> headerSet.addAll(s.keySet()));
        List<String> headers = new ArrayList<>(headerSet);

        // Determine column widths
        Map<String, Integer> columnWidths = new HashMap<>();
        for (String header : headers) {
            int max = header.length();
            for (Map<String, String> row : rows) {
                String value = row.getOrDefault(header, "");
                max = Math.max(max, value.length());
            }
            columnWidths.put(header, max + 2); // padding
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (String header: headers){
            sb.append("| ").append(padRight(header, columnWidths.get(header)-1));
        }
        sb.append(" |\n");

        // Header separator
        for (String header : headers) {
            sb.append("|").append("-".repeat(columnWidths.get(header)));
        }
        sb.append("|\n");
        for(Map<String, String> entry : rows){
            for (String header: headers){
                sb.append("| ").append(padRight(entry.get(header), columnWidths.get(header)-1));
            }
            sb.append(" |\n");
        }
        return sb.toString();
    }

    private static String padRight(String text, int len) {
        return String.format("%-" + len + "s", text);
    }
    public static String getMonthName(String dateStr) {
        if (dateStr == null || dateStr.length() != 8) {
            throw new IllegalArgumentException("Date must be in DDMMYYYY format");
        }

        int monthInt;
        try {
            monthInt = Integer.parseInt(dateStr.substring(2, 4));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }

        Month month = Month.fromInt(monthInt);
        return month.getDisplayName();
    }
}
