package sample;


public class IPv6 {
    private int version;
    private int trafficclass; // Traffic Class
    private int flowlabel; // flowlabel
    private int payloadlength = 0;
    private int nextheader = 0;
    private int hoplimit;
    private int protocol;
    private String sourceIP;
    private String destinationIP;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setVersion(String input) {
        int v = Integer.parseInt(input);
        setVersion(v);
    }

    public int getTrafficclass() {
        return trafficclass;
    }

    public void setTrafficclass(int trafficclass) {
        this.trafficclass = trafficclass;
    }

    public void setTrafficclass(String input) {
        int trafficclass = Integer.parseInt(input);
        setTrafficclass(trafficclass);
    }

    public int getFlowlabel() {
        return flowlabel;
    }

    public void setFlowlabel(int Flowlabel) {
        this.flowlabel = Flowlabel;
    }

    public void setFlowlabel(String input) {
        int Flowlabel = Integer.parseInt(input);
        setFlowlabel(Flowlabel);
    }

    public int getPayloadlength() {
        return payloadlength;
    }

    public void setPayloadlength(int Payload) {
        this.payloadlength = Payload;
    }

    public int getHoplimit() {
        return hoplimit;
    }

    public void setHoplimit(int Hoplimit) {
        this.hoplimit = Hoplimit;
    }

    public int getNextheader() {
        return nextheader;
    }

    public void setNextheader(int NextHeader) {
        this.nextheader = NextHeader;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }

    public String getDestinationIP() {
        return destinationIP;
    }

    public void setDestinationIP(String destinationIP) {
        this.destinationIP = destinationIP;
    }

    public void print() {
        System.out.println("\nHeader information:\n" + toSeperatedString());
    }

    public void printBinary() {
        try {
            System.out.println("\nBinary header information:");
            System.out.println(toBinary(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toBinary(IPv6 header) {
        Controller bHeader = new Controller();
        bHeader.setValue(header);
        return bHeader.getValue();
    }

    public String toSeperatedString() {
        char seperator = '-';
        String output =
                Integer.toString(version) + seperator + Integer.toString(trafficclass) + seperator
                        + Integer.toString(flowlabel) + seperator + Integer.toString(payloadlength) + seperator
                        + Integer.toString(nextheader) + seperator
                        + Integer.toString(hoplimit) + seperator
                        + sourceIP + seperator + destinationIP;
        return output;
    }
}
