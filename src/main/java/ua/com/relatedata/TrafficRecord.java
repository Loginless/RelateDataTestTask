package ua.com.relatedata;

import java.time.LocalDateTime;
import java.util.Objects;

public class TrafficRecord {

    private LocalDateTime dateTime;
    private Long bytesIn;
    private Long bytesOut;
    private Long packetsIn;
    private Long packetsOut;
    private String applicationName;
    private String destinationAddress;
    private String protocolNumber;
    private String sourceAddress;


    public TrafficRecord(LocalDateTime dateTime, Long bytesIn, Long bytesOut, Long packetsIn, Long packetsOut, String applicationName, String destinationAddress, String protocolNumber, String sourceAddress) {
        this.dateTime = dateTime;
        this.bytesIn = bytesIn;
        this.bytesOut = bytesOut;
        this.packetsIn = packetsIn;
        this.packetsOut = packetsOut;
        this.applicationName = applicationName;
        this.destinationAddress = destinationAddress;
        this.protocolNumber = protocolNumber;
        this.sourceAddress = sourceAddress;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getBytesIn() {
        return bytesIn;
    }

    public void setBytesIn(Long bytesIn) {
        this.bytesIn = bytesIn;
    }

    public Long getBytesOut() {
        return bytesOut;
    }

    public void setBytesOut(Long bytesOut) {
        this.bytesOut = bytesOut;
    }

    public Long getPacketsIn() {
        return packetsIn;
    }

    public void setPacketsIn(Long packetsIn) {
        this.packetsIn = packetsIn;
    }

    public Long getPacketsOut() {
        return packetsOut;
    }

    public void setPacketsOut(Long packetsOut) {
        this.packetsOut = packetsOut;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public Long getAmountOfBytes() {
        Long sumBytes = this.getBytesIn() + this.getBytesOut();
        return sumBytes;
    }

    public Long getAmountOfPackets() {
        Long sumPackets = this.getPacketsIn() + this.getPacketsOut();
        return sumPackets;
    }

    @Override
    public String toString() {
        return "ua.com.relatedata.TrafficRecord{" +
                "dateTime=" + dateTime +
                ", bytesIn=" + bytesIn +
                ", bytesOut=" + bytesOut +
                ", packetsIn=" + packetsIn +
                ", packetsOut=" + packetsOut +
                ", applicationName='" + applicationName + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", protocolNumber='" + protocolNumber + '\'' +
                ", sourceAddress='" + sourceAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrafficRecord)) return false;
        TrafficRecord that = (TrafficRecord) o;
        return Objects.equals(getDateTime(), that.getDateTime()) &&
                Objects.equals(getBytesIn(), that.getBytesIn()) &&
                Objects.equals(getBytesOut(), that.getBytesOut()) &&
                Objects.equals(getPacketsIn(), that.getPacketsIn()) &&
                Objects.equals(getPacketsOut(), that.getPacketsOut()) &&
                Objects.equals(getApplicationName(), that.getApplicationName()) &&
                Objects.equals(getDestinationAddress(), that.getDestinationAddress()) &&
                Objects.equals(getProtocolNumber(), that.getProtocolNumber()) &&
                Objects.equals(getSourceAddress(), that.getSourceAddress());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getDateTime(), getBytesIn(), getBytesOut(), getPacketsIn(), getPacketsOut(), getApplicationName(), getDestinationAddress(), getProtocolNumber(), getSourceAddress());
    }
}
