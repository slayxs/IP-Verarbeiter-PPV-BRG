package sample;
import java.util.Scanner;

public class IP_Rechner {

    public static void main(String[] args) {
            Scanner auswahl_rechner = new Scanner(System.in);
            boolean valid_rechner_option = false;
            System.out.println(
                "Willkommen zum IP-Verarbeiter! Wähle bitte aus.\n" +
                        "Drücke 1 für IPv4 \n" +
                        "Drücke 2 für IPv6\n");
            while (!valid_rechner_option) {
                //Scanner Option auswählen
                int option_r = auswahl_rechner.nextInt();
                if (option_r == 1) {
                    Scanner auswahl = new Scanner(System.in);
                    boolean valid_option = false;
                    System.out.println(
                            "IPv4 - Verarbeiter\n" +
                                    "Drücke 1 für Dezimal => Binär\n" +
                                    "Drücke 2 für Binär => Dezimal\n");
                    while (!valid_option) {
                        //Scanner Option auswählen
                        int option = auswahl.nextInt();
                        if (option == 1) {
                            valid_option = true;
                            System.out.println("Weitere Eingaben erforderlich...");
                            IPv4 header = new IPv4();
                            Checker valD = new Checker(header);
                            valD.setVersion();
                            valD.setIhl();
                            valD.setTos();
                            valD.setTotalLength();
                            valD.setID();
                            valD.setFlag();
                            valD.setFragmentOffset();
                            valD.setTtl();
                            valD.setProtocol();
                            valD.setSourceIp();
                            valD.setDestinationIp();
                            valD.computeChecksum();
                            header.print();
                            header.printBinary();


                            break;
                        } else if (option == 2) {
                            valid_option = true;
                            System.out.println("Weitere Eingaben benötigt:\n"
                                    + "Binärer Header:");
                            Controller bHeader = new Controller(Checker.fetchUserInput("Input:"));
                            System.out.print("\nDezimaler Header:\n"
                                    + bHeader.toDecimalHeaderString());
                            break;
                        } else {
                            System.out.println("Ungültige Angabe");
                        }
                    }
                }

                    else if (option_r == 2) {
                    Scanner auswahl = new Scanner(System.in);
                    boolean valid_option = false;
                    System.out.println(
                            "IPv6 - Verarbeiter\n" +
                                    "Drücke 1 für String => Binär\n" +
                                    "Drücke 2 für Binär => String\n");
                    while (!valid_option) {
                        //Scanner Option auswählen
                        int option = auswahl.nextInt();
                        if (option == 1) {
                            valid_option = true;
                            System.out.println("Weitere Eingaben erforderlich...");
                            IPv6 header = new IPv6();
                            Checker_v6 valD_v6 = new Checker_v6(header);
                            valD_v6.setVersion();
                            valD_v6.setTrafficclass();
                            valD_v6.setFlowlabel();
                            valD_v6.setPayloadLength();
                            valD_v6.setNextHeader();
                            valD_v6.setHopLimit();
                            valD_v6.setSourceIp();
                            valD_v6.setDestinationIp();
                            header.print();
                            header.printBinary();

                            break;
                        } else if (option == 2) {
                            valid_option = true;
                            System.out.println("Weitere Eingaben benötigt:\n"
                                    + "Binärer Header:");
                            Controller bHeader = new Controller(Checker.fetchUserInput("Input:"));
                            System.out.print("\nString Header:\n"
                                    + bHeader.toHexHeaderString());
                            break;
                        } else {
                            System.out.println("Ungültige Angabe");
                        }
                    }

                } else {
                    System.out.println("Ungültige Angabe");
                }



            }
        }
    }

