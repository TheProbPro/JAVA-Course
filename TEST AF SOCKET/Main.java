 
import java.io.*;
import java.net.ConnectException;
import java.util.Scanner;

public class Main {
    String IP;
    int Port;
    Scanner CommData = new Scanner(System.in);

    public Main()  // Constructor
    {
        // Siden jeg har pladsen, note: Bør muligvis splitte selection-tree til en seperat class, og kalde RobCom derfra.
        // Kan derved også indlejre billedbehandling mere direkte, og vil dermed have muligheden for at holde opdateringer adskildt.
        // Ville være en fordel at oprette seperate metoder til connect(), disconnect(), samt write.
        // Endvidere nok fordelagtigt hvis status() kunne tjekkes for sig, og evt. blev overtaget af en lokal variabel i RobComm, frem for det udleverede kode.
        // Bonus med sammenlægningen - bør også overvejes om den kan foregå i sin egen metode, så det kan holdes bare lidt OOP & pænt. -Vincent
    }
/*
    static public void entotre() {
        Main m1 = new Main();
        m1.Communications();

    }
*/
    public static void main(String[] args) throws IOException {
        System.out.println("Yay, statisk elektricitet:");
        //entotre(); Unødvendig omgåelse af OOP
        Main m1 = new Main();
        m1.Communications();
    }

    private void Communications() throws IOException, java.util.InputMismatchException{
        {
            int iptest = 0;
            while (iptest < 10) {  // Er det nu også nødvendigt? Kunne jeg ikke nøjes med at forvente at brugeren er halvvejs intelligent, og benytte case uden while-loop?
                try {
                    System.out.println("Indtast IP, default er 127.0.0.1");
                    IP = CommData.nextLine();
                    try {
                        System.out.println("Indtast port, default er 12345");
                        Port = CommData.nextInt();
                    } catch (Exception InputMismatchException) {
                        System.err.println("Port specificeret til ikke tal-værdi.");
                        CommData.nextLine(); // to reset the scanner
                    }
                } catch (Exception e) {
                    System.out.println("Noget gik galt. Sikker på at dit input er korrekt?");
                }

                System.out.println("Din input er IP: " + IP + " Og Port: " + Port + ".");
                System.out.println("\n");
                System.out.println("Hvis det  korrekt, bekræft med 1");
                System.out.println("Hvis det ikke er korrekt, tast 2");
                System.out.println("Hvis de ønsker default values, tast 3");
                System.out.println("Hvis du hedder Victor, og gerne vil have forbindelse til PLCen, vælg 4");
                int valg = CommData.nextInt();

                switch (valg) {
                    case 1:
                        System.out.println("Super, vi fortsætter.");
                        iptest = 15;
                        RobCli(IP, Port);
                        break;
                    case 2:
                        System.out.println("Det var ærgerligt. Prøv igen.");
                        CommData.nextLine();
                        break;
                    case 3:
                        IP = "127.0.0.1"; Port = 12345;  // Ignorer brugerindtastning, kunne være hvad som helst
                        RobCli(IP, Port);
                        CommData.nextLine();
                        iptest = 15;
                        break;
                    case 4:
                        IP = "192.168.0.3"; Port = 23345;
                        RobCli(IP,Port);
                        CommData.nextLine();
                        break;
                    default:
                        System.err.println("Unexpected item in the bagging area. Please try again");
                        CommData.nextLine();
                        break;
                }

            }
            /*RobCli(IP, Port);
            System.out.println(IP + " " + Port);*/
            // return true; - Fjernet, da det næppe bliver aktuelt ifht. OOP at returnere noget.
        }
    }

    private synchronized void RobCli(String ip, int port) throws IOException {
        int i = 1;
        boolean robStatus = false;
        Scanner robScan = new Scanner(System.in);
        RobotClient robotClient = new RobotClient(ip, port);
        System.out.println("Hej robot! Jeg blev kaldt med " + ip + " og " + port + ".");
        System.out.println("Forsøger at forbinde.");
        do {
            try {
                robotClient.connect();
                try {
                    wait(2500);
                } catch (Exception InterruptedException) {
                    // System.err.println("Fejl client connect timeout.");  // Ingen grund til at gøre noget ved sagen, det er forventet opførsel.
                }
                // robStatus = robotClient.isConnected();
                try {
                    robStatus = robotClient.isConnected();
                } catch (Exception e) {
                    System.err.println("Statuscheck gik galt. " + i + "\n");
                    System.err.println("Status: " + robStatus + " " + i);
                }
            } catch(Exception e){System.err.println("Fejl ved forbindelse et sted. Formodentligt er PLC i forkert status, eller forbindelsen blev afbrudt. \n"); /*
                 throw new ConnectException("Whoops");
                catch (ConnectException e) {
                System.out.println("Kan ikke forbinde, prøver igen. RobStatus : " + robStatus);*/
            }

            if(robStatus == false) {
                System.out.println("Forbinder... Forsøg " + i + "/25.");
            }
            i++;
        }
        while (robStatus == false && i < 25);  // Kører mindst en gang.
        if ((i == 25) && (robStatus == false)) {
            System.out.println("Ingen Forbindelse, er PLC tændt, program overført, og indstillinger korrekte?. Denne funktion blev kaldt med IP: " + ip + "; Port:" + port);  // Bliver ikke kaldt pt.
        }
        if (robStatus == true) {
            System.out.println("Hallelujah, the lord has come(AKA der er forbindelse). \n");
            i = 30;
        }
        System.out.println("Forbindelse oprettet, vælg besked at sende: \n 1. Kort besked (1 sæt koordinater) \n 2. Længere besked(2 sæt) \n 3. 3 sæt koordinater \n 4. 4 sæt. \n 5. Gæt. \n 10. Fuld linje[ADVARSEL : Det er en ordentlig mængde data, kunne låse PLCen].\n 11. Tast dine egne koordinater. Tilføjer automatisk mellemrum først.\n 12. Send fil.");
        int senddata;
        senddata = robScan.nextInt();
        robScan.nextLine();
        String Send1 = " X000 Y000 Z255";  // Første karakter skal være 0. Smart. ;)
        String Send2 = " X000 Y000 Z255 X000 Y001 Z255";
        String Send3 = " X000 Y000 Z255 X000 Y001 Z255 X000 Y002 Z255";
        String Send4 = " X000 Y000 Z255 X000 Y001 Z255 X000 Y002 Z255 X000 Y003 Z255";
        String Send5 = " X000 Y000 Z255 X000 Y001 Z255 X000 Y002 Z255 X000 Y003 Z255 X000 Y004 Z255";
        String Send10 = " X000 Y000 Z255 X000 Y001 Z255 X000 Y002 Z255 X000 Y003 Z255 X000 Y004 Z255 X000 Y005 Z255 X000 Y006 Z255 X000 Y007 Z255 X000 Y008 Z255 X000 Y009 Z255 X000 Y010 Z255 X000 Y011 Z255 X000 Y012 Z255 X000 Y013 Z255 X000 Y014 Z255 X000 Y015 Z255 X000 Y016 Z255 X000 Y017 Z255 X000 Y018 Z255 X000 Y019 Z255 X000 Y020 Z255 X000 Y021 Z255 X000 Y022 Z255 X000 Y023 Z255 X000 Y024 Z255 X000 Y025 Z255 X000 Y026 Z255 X000 Y027 Z255 X000 Y028 Z255 X000 Y029 Z255 X000 Y030 Z255 X000 Y031 Z255 X000 Y032 Z255 X000 Y033 Z255 X000 Y034 Z255 X000 Y035 Z255 X000 Y036 Z255 X000 Y037 Z255 X000 Y038 Z255 X000 Y039 Z255 X000 Y040 Z255 X000 Y041 Z255 X000 Y042 Z255 X000 Y043 Z255 X000 Y044 Z255 X000 Y045 Z255 X000 Y046 Z255 X000 Y047 Z255 X000 Y048 Z255 X000 Y049 Z255 X000 Y050 Z255 X000 Y051 Z255 X000 Y052 Z255 X000 Y053 Z255 X000 Y054 Z255 X000 Y055 Z255 X000 Y056 Z255 X000 Y057 Z255 X000 Y058 Z255 X000 Y059 Z255 X000 Y060 Z255 X000 Y061 Z255 X000 Y062 Z255 X000 Y063 Z255 X000 Y064 Z255 X000 Y065 Z255 X000 Y066 Z255 X000 Y067 Z255 X000 Y068 Z255 X000 Y069 Z255 X000 Y070 Z255 X000 Y071 Z255 X000 Y072 Z255 X000 Y073 Z255 X000 Y074 Z255 X000 Y075 Z255 X000 Y076 Z255 X000 Y077 Z255 X000 Y078 Z255 X000 Y079 Z255 X000 Y080 Z255 X000 Y081 Z255 X000 Y082 Z255 X000 Y083 Z255 X000 Y084 Z255 X000 Y085 Z255 X000 Y086 Z255 X000 Y087 Z255 X000 Y088 Z255 X000 Y089 Z255 X000 Y090 Z255 X000 Y091 Z255 X000 Y092 Z255 X000 Y093 Z255 X000 Y094 Z255 X000 Y095 Z255 X000 Y096 Z255 X000 Y097 Z255 X000 Y098 Z255 X000 Y099 Z255";
        String Send11;
        switch (senddata) {
            case 1:
                System.out.println("Sender følgende string: " + Send1 + "\n");
                robotClient.write(Send1);
                break;
            case 2:
                System.out.println("Sender følgende string: " + Send2 + "\n");
                robotClient.write(Send2);
                break;
            case 3:
                System.out.println("Sender følgende string: " + Send3 + "\n");
                robotClient.write(Send3);
                break;
            case 4:
                System.out.println("Sender følgende string: " + Send4 + "\n");
                robotClient.write(Send4);
                break;
            case 5:
                System.out.println("Sender følgende string: " + Send5 + "\n");
                robotClient.write(Send5);
                break;
            case 10:
                System.out.println("Well, du bad om det. Sender : " + Send10 + "\n");
                robotClient.write(Send10);
                break;
            case 11:
                System.out.println("Indtast koordinater:");
                Send11 = (" "+robScan.nextLine());
                try {
                    // System.out.println(Send11);
                    robotClient.write(Send11);
                } catch (Exception NullPointerException) {
                    System.err.println("Null happened : \"" + Send11 + "\"");
                }
                System.out.println("Har sendt : " + Send11);
                break;
            case 12:
                String fil = "C:\\Users\\nvigg\\Desktop\\Github\\Studieprojekt\\Communications\\src\\com\\company\\xoutput3.txt";
                int storrelseBuffer = 4 * 1024;  // bliver til 4Kb, i snit ligger vi <4k.

                try(BufferedReader koordBuff = new BufferedReader(new FileReader(fil), storrelseBuffer)){
                    /*int j = 0;
                    char[] laestData = new char[75];*/ // Implementer som mere intelligent læser/sammenlægger
                    String linje;
                    linje = koordBuff.readLine();
                    while (linje != null){System.out.print(linje + "; \n");
                        wait(4000);
                        try{robotClient.write(linje);}
                        catch(Exception e){System.err.println("Problem med at skrive linje til robot. Her er indhold af line: " +linje +"\n");}
                    linje = koordBuff.readLine();}
                } catch (InterruptedException | FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.print("Ikke genkendt input, prøv igen.");
        }
        int senddata1;
        System.out.println("\n Vælg 1. for at afslutte, 2 for at sende mere.\n");
        senddata1 = robScan.nextInt();
        switch (senddata1){
            default:
                robotClient.disconnect();
                break;
                case 2:
                    robotClient.disconnect();
                    System.out.print("Lukker socket, genskaber forbindelse.");
                    RobCli(IP, port);
                }
        System.out.println("Besked sendt. Lukker socket, starter forfra.\n");
        // System.out.println("Robstatus er = " + robotClient.isConnected());
        robotClient.disconnect();  // Ikke tydeligt at den tager effekt - i det mindste fra Javasiden af. Kan dog ses på PLCen.
        //System.out.println("Connected to PLC? :" + robotClient.isConnected());
    }


}


