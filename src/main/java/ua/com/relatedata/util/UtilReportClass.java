package ua.com.relatedata.util;

import ua.com.relatedata.TrafficRecord;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UtilReportClass {

    private CSVReader csvReader = new CSVReader();

    //Find top 10 repeating destinationAddress
    public Map<String, Long> getTopReceivers(String fileName) {

        List<TrafficRecord> inputData = csvReader.read(fileName);
        Map<String, Long> topDestinations = inputData
                .stream()
                .collect(Collectors.groupingBy(TrafficRecord::getDestinationAddress, Collectors.summingLong(TrafficRecord::getAmountOfBytes)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
        return topDestinations;
    }

    //Find top 10 repeating sourceAddress
    public Map<String, Long> getTopTransmitters(String fileName) {

        List<TrafficRecord> inputData = csvReader.read(fileName);
        Map<String, Long> topSources = inputData
                .stream()
                .collect(Collectors.groupingBy(TrafficRecord::getSourceAddress, Collectors.summingLong(TrafficRecord::getAmountOfBytes)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
        return topSources;
    }

    //Find top 10 repeating applicationName
    public Map<String, Long> getTopApplications(String fileName) {

        List<TrafficRecord> inputData = csvReader.read(fileName);
        Map<String, Long> topApplications = inputData
                .stream()
                .filter(Objects::nonNull)
                .filter(tr -> Objects.nonNull(tr.getApplicationName()))
                .collect(Collectors.groupingBy(TrafficRecord::getApplicationName, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
        return topApplications;
    }

    //Find top 3 used protocolNumber
    public Map<String, Long> getTopProtocols(String fileName) {

        List<TrafficRecord> inputData = csvReader.read(fileName);
        Map<String, Long> topProtocols = inputData
                .stream()
                .filter(Objects::nonNull)
                .filter(tr -> Objects.nonNull(tr.getProtocolNumber()))
                .collect(Collectors.groupingBy(TrafficRecord::getProtocolNumber, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
        return topProtocols;
    }

    //Returns Map with sum of in.out bytes for the protocols and applications between start/end time
    public Map<String, Long> getCTrafficByTimePeriod(String fileName, LocalDateTime startDateTime, LocalDateTime endDateTime) {

        List<TrafficRecord> inputData = csvReader.read(fileName);

        //Filer the source list by start/end time
        List<TrafficRecord> filteredList = inputData
                .stream()
                .filter(tr -> tr.getDateTime().isAfter(startDateTime) && tr.getDateTime().isBefore(endDateTime))
                .collect(Collectors.toList());

        //Get data sum of in/out traffic for the applications
        Map<String, Long> bytesForApp = filteredList
                .stream()
                .filter(Objects::nonNull)
                .filter(tr -> Objects.nonNull(tr.getApplicationName()))
                .collect(Collectors.groupingBy(TrafficRecord::getApplicationName, Collectors.summingLong(TrafficRecord::getAmountOfBytes)));

        //Get data sum of in/out traffic for the Protocols
        Map<String, Long> bytesForProtocol = filteredList
                .stream()
                .filter(Objects::nonNull)
                .filter(tr -> Objects.nonNull(tr.getApplicationName()))
                .collect(Collectors.groupingBy(TrafficRecord::getProtocolNumber, Collectors.summingLong(TrafficRecord::getAmountOfBytes)));


        Map<String, Long> filteredByTimeResult = Stream.of(bytesForApp, bytesForProtocol)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1,
                        LinkedHashMap::new));


        return filteredByTimeResult;
    }

}
