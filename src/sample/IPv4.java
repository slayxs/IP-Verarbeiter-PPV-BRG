package sample;


public class IPv4 {
    private int version;
    private int ihl; // IPheaderlength
    private int tos; // type of service
    private int totalLength;
    private int id;
    private String flag = "000";
    private int fragmentOffset;
    private int ttl;
    private int protocol;
    private int checksum;
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

    public int getIhl() {
        return ihl;
    }

    public void setIhl(int ihl) {
        this.ihl = ihl;
    }

    public void setIhl(String input) {
        int ihl = Integer.parseInt(input);
        setIhl(ihl);
    }

    public int getTos() {
        return tos;
    }

    public void setTos(int tos) {
        this.tos = tos;
    }
    public void setTos(String input) {
        int tos = Integer.parseInt(input);
        setTos(tos);
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(String input) {
        int id = Integer.parseInt(input);
        setId(id);
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getFragment_offset() {
        return fragmentOffset;
    }

    public void setFragmentOffset(int fragmentOffset) {
        this.fragmentOffset = fragmentOffset;
    }

    public void setFragment_offset(String input) {
        int offset = Integer.parseInt(input);
        setFragmentOffset(offset);
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public void setTtl(String input) {
        int ttl = Integer.parseInt(input);
        setTtl(ttl);
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getChecksum() {
        return checksum;
    }

    public void setChecksum(int checksum) {
        this.checksum = checksum;
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

    public String toBinary(IPv4 header) {
        Controller bHeader = new Controller();
        bHeader.setValue(header);
        return bHeader.getValue();
    }

    public String toBinary(IPv6 header) {
        Controller bHeader = new Controller();
        bHeader.setValue(header);
        return bHeader.getValue();
    }

    public String toSeperatedString() {
        char seperator = '-';
        String output =
                Integer.toString(version) + seperator + Integer.toString(ihl) + seperator
                        + Integer.toString(tos) + seperator + Integer.toString(totalLength) + seperator
                        + Integer.toString(id) + seperator + flag + seperator
                        + Integer.toString(fragmentOffset) + seperator + Integer.toString(ttl) + seperator
                        + Integer.toString(protocol) + seperator + Integer.toString(checksum) + seperator
                        + sourceIP + seperator + destinationIP;
        return output;
    }
}
