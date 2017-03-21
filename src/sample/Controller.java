package sample;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;


public class Controller {
        private String value;

    public Controller() {
        value = "0";
    }

    public Controller(String s) {
        value = s;
    }

    public Controller(int i) {
        value = Integer.toBinaryString(i);
    }

    public static Boolean isBinary(String s) {
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '0':
                    break;
                case '1':
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

        public void setValue(String s, int b) {
            value = leadingZero(s, b);
        }

        public void setValue(int i, int b){
            value = leadingZero(Integer.toBinaryString(i), b);
        }

        public void setValue(IPv4 header) {
            try {
                String[] ipPartsSourceIP = header.getSourceIP().split("\\.");
                String ipPartsSourceIPInBinary =
                        leadingZero(Integer.toBinaryString(Integer.parseInt(ipPartsSourceIP[0])), 8)
                                + leadingZero(Integer.toBinaryString(Integer.parseInt(ipPartsSourceIP[1])), 8)
                                + leadingZero(Integer.toBinaryString(Integer.parseInt(ipPartsSourceIP[2])), 8)
                                + leadingZero(Integer.toBinaryString(Integer.parseInt(ipPartsSourceIP[3])), 8);

                String[] ipPartsDestinationIP = header.getDestinationIP().split("\\.");
                String ipPartsDestinationIPInBinary =
                        leadingZero(Integer.toBinaryString(Integer.parseInt(ipPartsDestinationIP[0])), 8)
                                + leadingZero(Integer.toBinaryString(Integer.parseInt(ipPartsDestinationIP[1])), 8)
                                + leadingZero(Integer.toBinaryString(Integer.parseInt(ipPartsDestinationIP[2])), 8)
                                + leadingZero(Integer.toBinaryString(Integer.parseInt(ipPartsDestinationIP[3])), 8);
                char seperator = ' ';
                value = leadingZero(header.getVersion(), 4) + seperator;
                value += leadingZero(header.getIhl(), 4) + seperator;
                value += leadingZero(header.getTos(),8) + seperator;
                value += leadingZero(header.getTotalLength(), 16) + seperator;
                value += leadingZero(header.getId(), 16) + seperator;
                value += header.getFlag() + seperator;
                value +=  leadingZero(header.getFragment_offset(), 13) + seperator;
                value +=  leadingZero(header.getTtl(), 8) + seperator;
                value += leadingZero(header.getProtocol(), 8) + seperator;
                value +=  leadingZero(header.getChecksum(), 16) + seperator;
                value += ipPartsSourceIPInBinary + seperator;
                value += ipPartsDestinationIPInBinary;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public String hexToBinary(String hex) {
        int i = Integer.parseInt(hex, 16);
        String bin = Integer.toBinaryString(i);
        return bin;
    }

    public String getValue() {
        return value;
    }

    public void setValue(IPv6 header) {
        try {
            String[] ipPartsSourceIP = header.getSourceIP().split(":");
            String ipPartsSourceIPInBinary =
                   hexToBinary(ipPartsSourceIP[0]) + ":"
                            + hexToBinary(ipPartsSourceIP[1]) + ":"
                            + hexToBinary(ipPartsSourceIP[2]) + ":"
                            + hexToBinary(ipPartsSourceIP[3]) + ":"
                            + hexToBinary(ipPartsSourceIP[4]) + ":"
                            + hexToBinary(ipPartsSourceIP[5]) + ":"
                            + hexToBinary(ipPartsSourceIP[6]) + ":"
                            + hexToBinary(ipPartsSourceIP[7]);


            String[] ipPartsDestinationIP = header.getDestinationIP().split(":");
            String ipPartsDestinationIPInBinary =
                    hexToBinary(ipPartsDestinationIP[0]) + ":"
                            + hexToBinary(ipPartsDestinationIP[1]) + ":"
                            + hexToBinary(ipPartsDestinationIP[2]) + ":"
                            + hexToBinary(ipPartsDestinationIP[3]) + ":"
                            + hexToBinary(ipPartsDestinationIP[4]) + ":"
                            + hexToBinary(ipPartsDestinationIP[5]) + ":"
                            + hexToBinary(ipPartsDestinationIP[6]) + ":"
                            + hexToBinary(ipPartsDestinationIP[7]);
            char seperator = ' ';
            value = leadingZero(header.getVersion(), 4) + seperator;
            value += leadingZero(header.getTrafficclass(), 8) + seperator;
            value += leadingZero(header.getFlowlabel(),20) + seperator;
            value += leadingZero(header.getPayloadlength(), 1) + seperator;
            value += leadingZero(header.getNextheader(), 1) + seperator;
            value +=  leadingZero(header.getHoplimit(), 8) + seperator;
            value += ipPartsSourceIPInBinary + seperator;
            value += ipPartsDestinationIPInBinary;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public String leadingZero(String s, int length) {
            while (s.length() < length) {
                s = '0' + s;
            }
            return s;
        }

        public String leadingZero(int i, int length) {
            String s  = Integer.toBinaryString(i);
            while  (s.length() < length) {
                s = "0" + s;
            }
            return s;
        }

        public String toDecimalHeaderString() {
            try {
                char seperator = '-';
                char ctr = '.';
                String output = "";
                String sHeader = this.getValue();
                if (sHeader.contains(" ")) {
                    // "\\s" regex
                    String[] bHeaderArray = sHeader.split("\\s");
                    for (int i = 0; i < (bHeaderArray.length); i++) {
                        if (isBinary(bHeaderArray[i])) {
                            if (i == 5) {
                                output += bHeaderArray[i] + seperator;
                            }else if (i == bHeaderArray.length -2) { //source IP location
                                String ip1 = "";
                                for (int j = 0; j < bHeaderArray[i].length(); j+=8) {
                                    int dectemp = Integer.parseInt(bHeaderArray[i].substring(j, j+8), 2);
                                    if (j == 24) {
                                        ip1 += Integer.toString(dectemp);
                                        output += ip1 + seperator;
                                    } else {
                                        ip1 += Integer.toString(dectemp) + ctr;
                                    }
                                }
                            } else if (i == bHeaderArray.length -1) { //destination IP location
                                String ip2 = "";
                                for (int j = 0; j < bHeaderArray[i].length(); j+=8) {
                                    int dectemp = Integer.parseInt(bHeaderArray[i].substring(j, j+8), 2);
                                    if (j == 24) {
                                        ip2 += Integer.toString(dectemp);
                                        output += ip2;
                                    } else {
                                        ip2 += Integer.toString(dectemp) + ctr;
                                    }
                                }
                            } else {
                                int temp = Integer.parseInt(bHeaderArray[i],2);
                                output += Integer.toString(temp) + seperator;
                            }
                        } else {
                            throw new RuntimeException("The entered string is not in binary notation");
                        }
                    }
                    return output;
                    //entered string doesn't contain whitespace
                } else if (isBinary(sHeader)) {
                    output = "";
                    final List<Integer> fieldLengths = asList(
                            4, 4, 8, 16, 16, 3, 13, 8, 8, 16,
                            8, 8, 8, 8, //Source IP split
                            8, 8, 8, 8 // Destination IP split
                    );
                    int counter = 0;
                    ArrayList<Integer> decValues = new ArrayList<Integer>();
                    for (Integer field : fieldLengths) {
                        //parsing the bit segments per substring function to Integer (radix 2)
                        decValues.add(Integer.parseInt(sHeader.substring(counter, counter + field), 2));
                        counter += field;
                    }
                    //concatenate the first 5 fields to the output
                    for (int i = 0; i < 5; i++) {
                        output += decValues.get(i).toString() + seperator;
                    }
                    //get Flags from binary input
                    output += sHeader.substring(47, 50) + seperator;
                    //get next four fields after flags
                    for (int i = 6; i <= 9; i++) {
                        output += decValues.get(i).toString() + seperator;
                    }
                    String ip1 = "";
                    String ip2 = "";
                    for (int i = 10; i < decValues.size(); i++) {
                        //get next four fields = source IP
                        if (i < 13) {
                            ip1 += decValues.get(i).toString() + ctr;
                            //last concatenation without dot
                        } else if (i == 13) {
                            ip1 += decValues.get(i).toString();
                            //last concatenation without dot
                        } else if (i > 13 && (i == decValues.size() - 1)) {
                            ip2 += decValues.get(i).toString();
                        } else if (i > 13) {
                            ip2 += decValues.get(i).toString() + ctr;
                        }
                    }
                    output += ip1 + seperator + ip2;
                    return output;
                } else {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Fehler";
            }
        }

    public String toHexHeaderString() {
        char ctr = ':';
        int value;
        String output = "";
        String[] srcip_array;
        String[] destip_array;
        String srcip = "";
        String destip = "";
        String sHeader = this.getValue();
        if (sHeader.contains(" ")) {
            // "\\s" regex
            String[] bHeaderArray = sHeader.split("\\s");
            for (int i = 0; i < (bHeaderArray.length); i++) {
                if (i == 6) {
                    srcip_array = bHeaderArray[i].split(":");
                    for (int x = 0; x < (srcip_array.length); x++) {
                        if (x == 7) {
                            srcip += Integer.toString(Integer.parseInt(srcip_array[x], 2), 16);
                        } else {
                            srcip += Integer.toString(Integer.parseInt(srcip_array[x], 2), 16) + ":";
                        }


                    }
                } else if (i == 7) {
                    destip_array = bHeaderArray[i].split(":");
                    for (int x = 0; x < (destip_array.length); x++) {
                        if (x == 7) {
                            destip += Integer.toString(Integer.parseInt(destip_array[x], 2), 16);
                        } else {
                            destip += Integer.toString(Integer.parseInt(destip_array[x], 2), 16) + ":";
                        }


                    }

                } else {
                    value = Integer.parseInt(bHeaderArray[i], 2);

                    output += value + "-";


                }


            }
        }
        removeWhitespace(output);
        output = output + srcip + " " + destip;
        return output;
        //entered string doesn't contain whitespace
        }

    private String removeWhitespace(String b) {
        String s = b.replaceAll("\\s", "");
        return s;
        }
    }

