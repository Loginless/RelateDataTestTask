package ua.com.relatedata;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ua.com.relatedata.util.JsonUtil;
import ua.com.relatedata.util.UtilReportClass;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

public class main {

    public static void main(String[] args) {

        UtilReportClass utilReportClass = new UtilReportClass();
        ObjectMapper objectMapper = JsonUtil.getInstance();

        //Start and end date for the getTrafficByTimePeriod method
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy h:mm:ss.SSSSSSSSS a", Locale.ENGLISH);
        LocalDateTime start = LocalDateTime.parse("7/13/2018 5:23:22.000000000 PM", formatter);
        LocalDateTime end = LocalDateTime.parse("7/13/2018 5:31:01.000000000 PM", formatter);

        String sourceFileName = "";
        String destinationFileName = "";

        //Enter path to the source and destination files
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Please, enter a path to the source file:");
            sourceFileName = reader.readLine();
            System.out.println("Please, enter a path to the destination file:");
            destinationFileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Find top 10 traffic receivers
        Map<String, Long> topReceivers = utilReportClass.getTopReceivers(sourceFileName);

        //Find top 10 traffic transmitters
        Map<String, Long> topTransmitters = utilReportClass.getTopTransmitters(sourceFileName);

        //Find top 10 used applications
        Map<String, Long> topApplications = utilReportClass.getTopApplications(sourceFileName);

        //Find top 3 used protocols
        Map<String, Long> topProtocols = utilReportClass.getTopProtocols(sourceFileName);

        //Find amount of traffic for applications and protocols between the stated start/end time
        Map<String, Long> getTrafficByTimePeriod = utilReportClass.getCTrafficByTimePeriod(sourceFileName, start, end);


        //Convert Maps with results to string with Json data

        StringBuilder stringBuilder = new StringBuilder();
        ObjectWriter ow = objectMapper.writerWithDefaultPrettyPrinter();

        try {
            stringBuilder.append(ow.writeValueAsString(topReceivers) + "," + System.lineSeparator());
            stringBuilder.append(ow.writeValueAsString(topTransmitters) + "," + System.lineSeparator());
            stringBuilder.append(ow.writeValueAsString(topApplications) + "," + System.lineSeparator());
            stringBuilder.append(ow.writeValueAsString(topProtocols) + "," + System.lineSeparator());
            stringBuilder.append(ow.writeValueAsString(getTrafficByTimePeriod) + "," + System.lineSeparator());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //Write results to the destination file
        File file = new File(destinationFileName);

        try (
                OutputStream fileOutputStream = new FileOutputStream(file, true)
        ) {
            fileOutputStream.write(stringBuilder.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
