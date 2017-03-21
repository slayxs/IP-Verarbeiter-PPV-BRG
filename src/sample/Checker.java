package sample;



        import static java.util.Arrays.asList;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.util.List;

public class Checker {
    private IPv4 header = null;

    public Checker(IPv4 header1) {
        this.header = header1;
    }


    public void setVersion() {
        boolean valid = false;
        do {
            try {
                int version = fetchNumberInput("\nVersion:");
                if (version == 4) {
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

    public void setIhl() {
        boolean valid = false;
        do {
            try {
                int ihl = fetchNumberInput("IHL:");
                if (ihl >= 5 && ihl <= 15) { // minimum IHL is 5 (20 bytes); maximum 15 (60 bytes)
                    int ihl_byte = (ihl * 32) / 8;
                    header.setIhl(ihl);
                    System.out.println(">> IHL Auswahl = " + ihl + "."
                            +" The header length is " + ihl_byte + " bytes.");
                    valid = true;
                } else {
                    throw new RuntimeException("Außerhalb des Bereiches\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void setTotalLength() {
        // payload is neglected in this task: totalLength = ihl
        try {
            System.out.println("Payload = 0.");
            header.setTotalLength(header.getIhl());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setTos() {
        boolean valid = false;
        //		0, 32, 40, 56, 72, 88, 96, 112, 136, 144, 152, 160, 184, 192, 224
        final List<Integer> validTOS = asList(
                0, 8, 10, 14, 18, 22, 24, 28, 34, 36, 38, 40, 46, 48, 56
        );
        do {
            try {
                int tos = fetchNumberInput("TOS:");
                if (validTOS.contains(tos)) {
                    header.setTos(tos);
                    valid = true;
                } else
                    throw new RuntimeException("Ungültiger Type of Service\n");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void setID() {
        boolean valid = false;
        do {
            try {
                int id = fetchNumberInput("ID:");
                if (id >= 0 && id <= 65535) {
                    header.setId(id);
                    valid = true;
                } else {
                    throw new RuntimeException("ID nicht im 16bit Bereich");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void setFlag() {
        // 3 bit value; first bit is reserved for future use
        final List<String> validFlags = asList(
                "001",
                "010",
                "000"
        );
        boolean valid = false;
        do {
            try {
                String flag = fetchUserInput("Flag:");
                if (validFlags.contains(flag)) {
                    header.setFlag(flag);
                    valid = true;
                } else {
                    throw new Exception("Falscher Flag Input\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void setFragmentOffset() {
        boolean valid = false;
        do {
            try {
                int offset = fetchNumberInput("Offset:");
                header.setFragmentOffset(offset);
                valid = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void setTtl() {
        boolean valid = false;
        do {
            try {
                int ttl = fetchNumberInput("TTL:");
                if (ttl > 0) {
                    header.setTtl(ttl);
                    valid = true;
                } else {
                    throw new RuntimeException(">> The packet is expired and will be discarded.\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void setProtocol() {
        boolean valid = false;
        do {
            try {
                int protocol = fetchNumberInput("Protocol:");
                if (protocol >= 0 & protocol <= 142) {
                    header.setProtocol(protocol);
                    valid = true;
                } else {
                    throw new RuntimeException(">> Invalid protocol number.\r".concat(
                            ">> Please refer to http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml \n"));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void setSourceIp() {
        boolean valid = false;
        do {
            String ip = fetchUserInput("Source IP adress (ex.: 192.168.1.2):");
            valid = validateIPadress(ip);
            header.setSourceIP(ip);
        } while (!valid);
    }

    public void setDestinationIp() {
        boolean valid = false;
        do {
            String ip = fetchUserInput("Destination IP adress (ex.: 192.168.1.4):");
            valid = validateIPadress(ip);
            header.setDestinationIP(ip);
        } while (!valid);
    }

    public void computeChecksum() {
        computeAnim();
        Integer header_checksum;
        header_checksum = header.getVersion() + header.getIhl()
                + header.getTos() + header.getId()
                + Integer.parseInt(header.getFlag(), 2) + header.getFragment_offset()
                + header.getTtl() + header.getProtocol() + // checksum handled as 0//separate values from source IP
                getIntValueFromIP(header.getSourceIP(),0)	+ getIntValueFromIP(header.getSourceIP(),1)
                +getIntValueFromIP(header.getSourceIP(), 2) + getIntValueFromIP(header.getSourceIP(), 3)
                //separate values from destination IP
                + getIntValueFromIP(header.getDestinationIP(), 0) + getIntValueFromIP(header.getDestinationIP(), 1)
                + getIntValueFromIP(header.getDestinationIP(), 2) + getIntValueFromIP(header.getDestinationIP(), 3);
        if (header_checksum != null) {
            header.setChecksum(header_checksum);
            System.out.println(
                    "\n>>Our fancy algorithm computed the packet checksum: \n" + header_checksum
                            + " binary: " + Integer.toBinaryString(header_checksum)
            );
        }
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
                System.out.println(">>Please enter a valid number.\n");
            }
        } while (number == null);
        return number;
    }
    private boolean  validateIPadress(String ip_adress) {
        boolean valid = false;
        try {
            String ip = ip_adress;
            //IP address has to be in notation X.X.X.X of max 32bit length
            if (ip.contains(".")) {
                String[] ip_parts = ip.split("\\.");
                if (ip_parts.length != 4) {
                    throw new RuntimeException(">>Invalid length of IP adress!\n");
                }
                for (String string : ip_parts) {
                    int ip_part = Integer.parseInt(string);
                    //maximum 8bit value
                    if (ip_part < 0 || ip_part > 255) {
                        throw new RuntimeException(">>Invalid IP adress!\n");
                    }
                }
                if (Integer.parseInt(ip_parts[3]) == 0) {
                    throw new RuntimeException("You entered the networks IP address. Please enter a host IP.");
                }
                valid = true;
            } else {
                throw new RuntimeException(">>Please format your input appropriately.\n");
            }
        } catch (NumberFormatException nfe) {
            System.out.println(">>Please enter valid numbers for the ip adress.\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;
    }

    private int getIntValueFromIP(String ip_adress, int value_position) {
        String[] ip_adress_values = ip_adress.split("\\.");
        int[] intarray = {
                Integer.parseInt(ip_adress_values[0]), Integer.parseInt(ip_adress_values[1]),
                Integer.parseInt(ip_adress_values[2]), Integer.parseInt(ip_adress_values[3])
        };
        return intarray[value_position];
    }

    private void computeAnim() {
        int switcher = 0;
        System.out.println("IP Header vollständig");
        String message = "Wird berechnet...";
        System.out.println(message);
        for (int i = 0; i < 8; i++) {
            switch (switcher) {
                case 0: System.out.print(message + "  \r");
                    switcher = 1;
                    break;
                case 1: System.out.print(message + ". \r");
                    switcher = 2;
                    break;
                case 2: System.out.print(message + "..\r");
                    switcher = 0;
                    break;
                default:
                    break;
            }
        }
    }

}