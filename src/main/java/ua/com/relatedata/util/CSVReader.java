package ua.com.relatedata.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ua.com.relatedata.TrafficRecord;

import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CSVReader {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy h:mm:ss.SSSSSSSSS a", Locale.ENGLISH);

    List<TrafficRecord> read(String filename) {

        List<TrafficRecord> inputRecords = new ArrayList<>();
        try (Reader in = new FileReader(filename)) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().withNullString("").parse(in);
            for (CSVRecord record : records) {
                inputRecords.add(new TrafficRecord(
                        LocalDateTime.parse(record.get(ReportHeaders.date), formatter),
                        Long.parseLong(record.get(ReportHeaders.bytesIn)),
                        Long.parseLong(record.get(ReportHeaders.bytesOut)),
                        Long.parseLong(record.get(ReportHeaders.packetsIn)),
                        Long.parseLong(record.get(ReportHeaders.packetsOut)),
                        record.get(ReportHeaders.applicationName),
                        record.get(ReportHeaders.destinationAddress),
                        record.get(ReportHeaders.protocolNumber),
                        record.get(ReportHeaders.sourceAddress)));
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return inputRecords;
    }


}
