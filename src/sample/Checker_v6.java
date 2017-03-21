package sample;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import static java.util.Arrays.asList;

public class Checker_v6 {

    private IPv6 header = null;
    public Checker_v6(IPv6 header2) {
        this.header = header2;
    }


    public void setVersion() {
        boolean valid = false;
        do {
            try {
                int version = fetchNumberInput("\nVersion:");
                if (version == 6) {
                    header.setVersion(version);
                    valid = true;
                } else {
                    throw new RuntimeException("Falsche Version\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void setTrafficclass() {
        boolean valid = false;
        do {
            try {
                int trafficclass = fetchNumberInput("Traffic Class:");
                if (trafficclass >= 0 && trafficclass <= 255) { // Minimum is 0 max 255
                    int trafficclass_byte = (trafficclass * 32) / 8;
                    header.setTrafficclass(trafficclass);
                    System.out.println(">> trafficclass Auswahl = " + trafficclass);
                    valid = true;
                } else {
                    throw new RuntimeException("Außerhalb des Bereiches\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void setFlowlabel() {
        boolean valid = false;
        do {
            try {
                int flowlabel = fetchNumberInput("Flowlabel:");
                if (flowlabel >= 0 && flowlabel <= 1048575){ //2^20
                    header.setFlowlabel(flowlabel);
                    valid = true;
                } else {
                    throw new RuntimeException("Außerhalb des Bereiches\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void setPayloadLength() {
                    header.setPayloadlength(0);
    }

    public void setNextHeader() {
        header.setNextheader(0);
    }

    public void setHopLimit() {
        boolean valid = false;
        do {
            try {
                int hoplimit = fetchNumberInput("HopLimit");
                if (hoplimit >= 0 && hoplimit <= 255) { // Min 0, Max 255
                    int hoplimit_byte = (hoplimit * 32) / 8;
                    header.setHoplimit(hoplimit);
                    System.out.println(">> HopLimit Auswahl = " + hoplimit + "."
                            +" The header length is " + hoplimit + " bytes.");
                    valid = true;
                } else {
                    throw new RuntimeException("Außerhalb des Bereiches\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void setSourceIp() {
        boolean valid = true;
        do {
            String ip = fetchUserInput("Source IP adress (Bsp.: fe80::9826:61ff:fe32:db68):");
            header.setSourceIP(ip);
        } while (!valid);
    }

    public void setDestinationIp() {
        boolean valid = true;
        do {
            String ip = fetchUserInput("Destination IP adress (Bsp.: fe80::9826:61ff:fe32:db68):");
            header.setDestinationIP(ip);
        } while (!valid);
    }


    public static String fetchUserInput(String message) {
        String input = "";
        System.out.print(message);
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
            input = bReader.readLine();
        } catch (Exception e) {
            e.getMessage();
        }
        return input;
    }

    public static int fetchNumberInput(String message) {
        Integer number = null;
        do {
            try {
                number = Integer.parseInt(fetchUserInput(message));
            } catch (Exception e) {
                System.out.println("Bitte gebe eine gültige Nummer an\n");
            }
        } while (number == null);
        return number;
    }

}