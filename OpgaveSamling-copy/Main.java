     
    
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
    

    
    
public class Main {
   //imagePath is the path to the picture that should be worked on
   private String imagePath;
   String IP;
   int Port;
   Scanner CommData = new Scanner(System.in);
   
   //System.out.println("point 1");
   public Main()  // Constructor
   {

   }
   
   
   static public void entotre() {
       Main m1 = new Main();
       m1.Communications();
       System.out.println("point 1");
       
   }
   
   //public static void main(String[] args) throws IOException {
   public void main(String imagePath) throws IOException {this.imagePath = imagePath;
        System.out.println("Yay, statisk elektricitet:");
        
       System.out.println("point 2");
        BufferedImage binary = null;
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            binary = new BufferedImage(img.getWidth(), img.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g = binary.createGraphics();
            g.drawImage(img, 0, 0, null);
            HashSet<Integer> colors = new HashSet<>();
            int color;
            for (int y = 0; y < binary.getHeight(); y++) {
                for (int x = 0; x < binary.getWidth(); x++) {
                    color = binary.getRGB(x, y);

                    colors.add(color);
                }
            }
            System.out.println(colors.size());
        } catch (IOException e) {
        }
        try {
            File s = new File("New" + imagePath);
            ImageIO.write(binary, "jpg", s);
            System.out.println("File writing completed ");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        File input = new File("New" + imagePath);
        BufferedImage image = ImageIO.read(input);
        BufferedImage resized = resize(image, binary.getHeight()/1, binary.getWidth()/1);
        File output = new File("New" + imagePath);
        ImageIO.write(resized, "jpg", output);
        int[][] compute = compute(output);
        //EdgeDetector edgdetect1 = new 
        //Picture picture0 = new Picture(imagePath);
        
        
        System.out.println("point 3");
        System.out.println(imagePath);
        entotre();
        
   }
 
   private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        System.out.println("point 4");
        return resized;
        
   }  
   
  public static int[][] compute(File file) throws IOException {
       System.out.println("point 5");
    BufferedImage image = ImageIO.read(file);
    Raster raster = image.getData();
    int w = raster.getWidth(), h = raster.getHeight();
    int drawLength = 0;
    int pixels[][] = new int[w][h];
    for (int x = 0; x < w; x++) {
        for (int y = 0; y < h; y++) {

            pixels[x][y] = raster.getSample(x, y, 0);
        }
    }
    String stringx;
    
     //Write picture as 1 and 0
    
    try (
            PrintStream output = new PrintStream(new File("xoutput.txt"));) {
                for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (pixels[x][y] < 125) {

                    stringx = "1";

                } else {
                    stringx = "0";

                }
                output.print(stringx);

            }

            output.print("\n");
            
            
        }
        
        output.close();
    } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
    }
           
    //Write picture as G code
    try (
            PrintStream output = new PrintStream(new File("xoutput8.txt"));) {
                for (int y = 0; y < h; y++) {
                    for (int x = 0; x < w; x++) {
                        if (pixels[x][y] < 125) 
                        {
                            drawLength = drawLength +1;
                            stringx = "G1 " + x + ", " + y + ", ";

                        }
                        
                        else if (drawLength > 100) 
                        {
                            stringx = "G3 " + x + ", " + y + ", ";
                            drawLength = 0;
                        }
                        
                        else
                        {
                            stringx = "G2 " + x + ", " + y + ", ";

                        }
            output.print(stringx);
        }
        output.print("\n");
    }
    
    output.close();
    } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
    }
   

        try (
                PrintStream output = new PrintStream(new File("xoutput3.txt"));) {
                    stringx = " ";
                    output.print(stringx);
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (pixels[x][y] < 125) 
                    {
                        stringx = "";
                        
                        if (y<10 & x<10)
                        stringx = "X" + "00" + y + " " + "Y" + "00" + x + " " + "Z" + "000 ";
                        
                        if (y>=10 & y<100 & x<10)
                        stringx = "X" + "0" + y + " " + "Y" + "00" + x + " " + "Z" + "000 ";
                        
                        if (y>=10 & y<100 & x>=10 & x<100)
                        stringx = "X" + "0" + y + " " + "Y" + "0" + x + " " + "Z" + "000 ";
                        
                        if (y<10 & x>=10 & x<100)
                        stringx = "X" + "00" + y + " " + "Y" + "0" + x + " " + "Z" + "000 ";
                        
                        if (y>=100 & x>=100)
                        stringx = "X" + y + " " + "Y" + x + " " + "Z" + "000 ";
                    } 
                    else
                    {
                        stringx = "";
                        
                        if (y<10 & x<10)
                        stringx = "X" + "00" + y + " " + "Y" + "00" + x + " " + "Z" + "255 ";
                        
                        if (y>=10 & y<100 & x<10)
                        stringx = "X" + "0" + y + " " + "Y" + "00" + x + " " + "Z" + "255 ";
                        
                        if (y>=10 & y<100 & x>=10 & x<100)
                        stringx = "X" + "0" + y + " " + "Y" + "0" + x + " " + "Z" + "255 ";
                        
                        if (y<10 & x>=10 & x<100)
                        stringx = "X" + "00" + y + " " + "Y" + "0" + x + " " + "Z" + "255 ";
                        
                        if (y>=100 & x>=100)
                        stringx = "X" + y + " " + "Y" + x + " " + "Z" + "255 ";
                    }
                    output.print(stringx);

                }
                output.print("\n");              
                
            }
            stringx = "\\0";
            output.print(stringx);
           
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    
        
           /*     try (
                PrintStream output = new PrintStream(new File("xoutput15.txt"));) {
                    stringx = " ";
                    output.print(stringx);
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int i = 0;
                    stringx = "";
                    if (pixels[x][y] < 125) 
                    {
                        
                        for (i = 1; pixels[x][y] < 125; i++)
                        {
                            if(pixels[x+i][y] > 125){
                            stringx = Integer.toString(i) + " ";
                            break;
                        }
                            //stringx = Integer.toString(i) + " ";
                            break;
                        }
                        
                        //stringx = "";
                        
                        //for (i = 0; pixels[x+i][y] < 125; i++){
                            
                            
                        //stringx = "i = " + Integer.toString(i) + " ";
                        //i = i + 1;
                        //if (pixels[x+i+1][y] > 125)
                        //{
                        //    stringx = "hvid ";
                            
                        //}
                        
                    //}
                    //stringx = Integer.toString(i) + " ";
                        //if (y<10 & x<10)
                        //stringx = "X" + "00" + y + " " + "Y" + "00" + x + " " + "Z" + "000 " + "i = " + Integer.toString(i) + " ";
                        
                        //if (y>=10 & y<100 & x<10)
                        //stringx = "X" + "0" + y + " " + "Y" + "00" + x + " " + "Z" + "000 ";
                        
                        //if (y>=10 & y<100 & x>=10 & x<100)
                        //stringx = "X" + "0" + y + " " + "Y" + "0" + x + " " + "Z" + "000 ";
                        
                        //if (y<10 & x>=10 & x<100)
                        //stringx = "X" + "00" + y + " " + "Y" + "0" + x + " " + "Z" + "000 ";
                        
                        //if (y>=100 & x>=100)
                        //stringx = "X" + y + " " + "Y" + x + " " + "Z" + "000 ";
                    } 
                    else
                    {
                        //stringx = "Hvid";
                        
                        //stringx = "";
                        
                        //if (y<10 & x<10)
                        //stringx = "X" + "00" + y + " " + "Y" + "00" + x + " " + "Z" + "255 ";
                        
                        //if (y>=10 & y<100 & x<10)
                        //stringx = "X" + "0" + y + " " + "Y" + "00" + x + " " + "Z" + "255 ";
                        
                        //if (y>=10 & y<100 & x>=10 & x<100)
                        //stringx = "X" + "0" + y + " " + "Y" + "0" + x + " " + "Z" + "255 ";
                        
                        //if (y<10 & x>=10 & x<100)
                        //stringx = "X" + "00" + y + " " + "Y" + "0" + x + " " + "Z" + "255 ";
                        
                        //if (y>=100 & x>=100)
                        //stringx = "X" + y + " " + "Y" + x + " " + "Z" + "255 ";
                    }
                    output.print(stringx);

                }
                //output.print("\n");              
                
            }
            stringx = "\\0";
            output.print(stringx);
           
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        */
        
        /*        try (
                PrintStream output = new PrintStream(new File("xoutput11.txt"));) {
                    stringx = " ";
                    output.print(stringx);
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    /*if (pixels[x][y] < 125)                                              
                    {   
                        ///int q = 0;
                        
                        for (int i = 1; pixels[x+i][y] < 125; i++) {
                        
                            //q = i + x;
                            stringx = Integer.toString(i) + " ";
                            //stringx = "yyyyyy";
                            if (pixels[x+i][y] > 125){
                                stringx = Integer.toString(i);
                                break;
                            }
                            if (pixels[x+i+1][y] > 125)
                            {
                                
                                //stringx = "In code";
                                break;
                                //if (y<10 & x<10){
                                //    stringx = "X" + "00" + y + " " + "Y" + "00" + x + " " + "Z" + "255 " + "X" + "00" + y + " " + "Y" + "00" + x + " " + "Z" + "000 " + "X" + "00" + y + " " + "Y" + "00" + q + " " + "Z" + "000 " + "X" + "00" + y + " " + "Y" + "00" + q + " " + "Z" + "255 ";
                                    //stringx = "X" + "00" + y + " " + "Y" + "00" + x + " " + "Z" + "000 ";
                                    //stringx = "X" + "00" + y + " " + "Y" + "00" + x + " " + "Z" + "000 ";
                                    //stringx = "X" + "00" + y + " " + "Y" + "00" + x + " " + "Z" + "255 ";
                                    
                            //}
                            //if (y>=10 & y<100 & x<10){
                                //stringx = "X" + "0" + y + " " + "Y" + "00" + x + " " + "Z" + "255 " + "X" + "0" + y + " " + "Y" + "00" + x + " " + "Z" + "000 " + "X" + "0" + y + " " + "Y" + "00" + q + " " + "Z" + "000 " + "X" + "0" + y + " " + "Y" + "00" + q + " " + "Z" + "255 ";
                            //}
                            //if (y>=10 & y<100 & x>=10 & x<100){
                                //stringx = "X" + "0" + y + " " + "Y" + "0" + x + " " + "Z" + "255 " + "X" + "0" + y + " " + "Y" + "0" + x + " " + "Z" + "000 " + "X" + "0" + y + " " + "Y" + "0" + q + " " + "Z" + "000 " + "X" + "0" + y + " " + "Y" + "0" + q + " " + "Z" + "255 ";
                            //}
                            //if (y<10 & x>=10 & x<100){
                                //stringx = "X" + "00" + y + " " + "Y" + "0" + x + " " + "Z" + "255 " + "X" + "00" + y + " " + "Y" + "0" + x + " " + "Z" + "000 " + "X" + "00" + y + " " + "Y" + "0" + q + " " + "Z" + "000 " + "X" + "00" + y + " " + "Y" + "0" + q + " " + "Z" + "255 ";
                            //}
                            //if (y>=100 & x>=100){
                                //stringx = "X" + y + " " + "Y" + x + " " + "Z" + "255 " + "X" + y + " " + "Y" + x + " " + "Z" + "000 " + "X" + y + " " + "Y" + q + " " + "Z" + "000 " + "X" + y + " " + "Y" + q + " " + "Z" + "255 ";   
                            //}
                        //x = q;
                        
                    }*/
                    //if (
                        //if (pixels[x+i][y] > 125)
                        //stringx = "xxxxxx";
                        //stringx = Integer.toString(i);
                        //for ( i = 1; pixels[x+i][y] < 125; i++)
                        
                        /*
                        if (y<10 & x<10)
                        stringx = "X" + "00" + y + " " + "Y" + "00" + x + " " + "Z" + "000 ";
                        
                        if (y>=10 & y<100 & x<10)
                        stringx = "X" + "0" + y + " " + "Y" + "00" + x + " " + "Z" + "000 ";
                        
                        if (y>=10 & y<100 & x>=10 & x<100)
                        stringx = "X" + "0" + y + " " + "Y" + "0" + x + " " + "Z" + "000 ";
                        
                        if (y<10 & x>=10 & x<100)
                        stringx = "X" + "00" + y + " " + "Y" + "0" + x + " " + "Z" + "000 ";
                        
                        if (y>=100 & x>=100)
                        stringx = "X" + y + " " + "Y" + x + " " + "Z" + "000 ";*/
                    //} 
                    /*else
                    {
                        stringx = "";
                        
                        /*if (y<10 & x<10)
                        stringx = "X" + "00" + y + " " + "Y" + "00" + x + " " + "Z" + "255 ";
                        
                        if (y>=10 & y<100 & x<10)
                        stringx = "X" + "0" + y + " " + "Y" + "00" + x + " " + "Z" + "255 ";
                        
                        if (y>=10 & y<100 & x>=10 & x<100)
                        stringx = "X" + "0" + y + " " + "Y" + "0" + x + " " + "Z" + "255 ";
                        
                        if (y<10 & x>=10 & x<100)
                        stringx = "X" + "00" + y + " " + "Y" + "0" + x + " " + "Z" + "255 ";
                        
                        if (y>=100 & x>=100)
                        stringx = "X" + y + " " + "Y" + x + " " + "Z" + "255 ";*/
/*                    }
                    output.print(stringx);

                }
                //output.print("\n");              
                
            }
            stringx = "\\0";
            output.print(stringx);
           
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }*/
        
        
    try (
            PrintStream output = new PrintStream(new File("xoutput4.txt"));) {
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (pixels[x][y] < 125 && pixels[x+1][y] < 125) 
                {
                    
                    stringx = "[" + x + "," + y + "," + pixels[x][y] + "]" + ",";
                    

                } 
                else 
                {
                    stringx = "[" + x + "," + y + "," + pixels[x][y] + "]" + ",";

                }
                output.print(stringx);

            }

            output.print("\n");
            
            
        }

       
        output.close();
    } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
    }
    

    /*try (
         PrintStream output = new PrintStream(new File("xoutput6.txt"));) {
             for (int y = 1; y < h; y++) {
                 for (int x = 1; x < w; x++) {
                    if (drawLength == 100) {
                        stringx = "G3 " + x + ", " + y + ", ";
                    }                  
                    else if (pixels[x][y] < 125 && pixels[x + 1][y] > 125 && pixels[x + 1][y + 1] > 125 && pixels[x][y + 1] > 125 && pixels[x - 1][y + 1] > 125  && pixels[x - 1][y] > 125 && pixels[x - 1][y - 1] > 125  && pixels[x][y - 1] > 125) {
                        stringx = "G1 " + x + ", " + y + ", ";
                        drawLength = drawLength +1;
                    }
                    else if (pixels[x + 1][y] < 125) {
                        stringx = "G1 " + x + ", " + y + ", " + "G1 " + x+1 + ", " + y + ", ";
                        x = x + 1;
                        drawLength = drawLength +1;
                    }
                    else if (pixels[x][y + 1] < 125) {
                        stringx = "G1 " + x + ", " + y + ", " + "G1 " + x + 1 + ", " + y + 1 + ", ";
                        drawLength = drawLength +1;
                    }
                    else if (pixels[x + 1][y + 1] < 125) {
                        stringx = "G1 " + x + ", " + y + ", " + "G1 " + x + 1 + ", " + y + 1 + ", ";
                        drawLength = drawLength +1;
                    }
                    else if (pixels[x + 1][y + 1] < 125) {
                        stringx = "G1 " + x + ", " + y + ", " + "G1 " + x + 1 + ", " + y + 1 + ", ";
                        drawLength = drawLength +1;
                    }
                    else {
                        stringx = "G2 " + x + ", " + y + ", ";
                    }                  
                    output.print(stringx);
                }
                output.print("\n");
            }
            
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }*/

        /*                            drawLength = drawLength +1;
                        if (pixels[x + 1][y] < 125) {
                        stringx = "G1 " + x + ", " + y + ", ";
                        stringx = "G1 " + x+1 + ", " + y + ", ";
                        }
                        if (pixels[x][y + 1] < 125) {
                        stringx = "G1 " + x + ", " + y + ", ";
                        stringx = "G1 " + x + 1 + ", " + y + 1 + ", ";
                        }
                        if (pixels[x + 1][y + 1] < 125) {
                        stringx = "G1 " + x + ", " + y + ", ";
                        stringx = "G1 " + x + 1 + ", " + y + 1 + ", ";
                        }
                        if (pixels[x + 1][y + 1] < 125) {
                        stringx = "G1 " + x + ", " + y + ", ";
                        stringx = "G1 " + x + 1 + ", " + y + 1 + ", ";
                        }
          */
         System.out.println("point 7");
    return null;

}


    private boolean Communications() {
        System.out.println("point 8");
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
                        System.out.println("Ikke et tal.");
                        CommData.nextLine(); // to reset the scanner
                    }
                } catch (Exception e) {
                    System.out.println("Noget gik galt. Sikker på at dit input er korrekt?");
                }
                System.out.println("point 9");
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
                        RobCli("127.0.0.1", 12345);
                        CommData.nextLine();
                        iptest = 15;
                        break;
                    case 4:
                        RobCli("192.168.0.3",23345);
                        CommData.nextLine();
                        break;
                    default:
                        System.out.println("Unexpected item in the bagging area. Please try again");
                        CommData.nextLine();
                        break;
                }

            }
            RobCli(IP, Port);
            System.out.println(IP + " " + Port);
            return true;
        }
    }
    
        private void RobCli(String ip, int port) {
        int i = 1;
        boolean robStatus = false;
        RobotClient robotClient = new RobotClient(ip, port);
        System.out.println("Hej robot! Jeg blev kaldt med " + ip + " og " + port + ".");
        System.out.println("Forsøger at forbinde.");
        System.out.println("point 10");
        do {
            try {
                robotClient.connect();
                try {
                    wait(5000);
                } catch (Exception e) {
                    System.out.println("Fejl client connect");
                }
                robStatus = robotClient.isConnected();
                throw new ConnectException("Whoops");
            } catch (ConnectException e) {
                System.out.println("Kan ikke forbinde, prøver igen. RobStatus : " + robStatus);
            }
            System.out.println("Forbinder... Forsøg " + i + "/25.");
            // robStatus = robotClient.isConnected();
            try {
                robStatus = robotClient.isConnected();
            } catch (Exception e) {
                System.out.println("Statuscheck gik galt. " + i + "\n");
            System.out.println("Status: " + robStatus + " " + i);}
            i++;
            try {
                robStatus = robotClient.isConnected();
            } catch (Exception e) {
                System.out.println("Det gik galt. " + i + "\n");
            }
        }
        while (robStatus == false && i > 26);
        if ((i == 25) && (robStatus == false)) {
            System.out.println("Ingen Forbindelse, er PLC tændt, program overført, og indstillinger korrekte?. Denne funktion blev kaldt med IP:Port: " + ip + ":" + port);  // Bliver ikke kaldt pt.
        }
        if (robStatus == true) {
            System.out.println("Hallelujah, the lord has come(AKA der er forbindelse). \n");
            i = 30;
        }
        System.out.println("Forbindelse oprettet, vælg besked at sende: \n 1. Kort besked (1 sæt koordinater) \n 2. Længere besked(2 sæt) \n 3. 3 sæt koordinater \n 4. 4 sæt. \n 5. Gæt. \n 10. Fuld linje[ADVARSEL : Det er en ordentlig røvfuld data, kunne låse PLCen].");
        int senddata = 0;
        try { senddata = CommData.nextInt();} catch (Exception e) {
            System.out.println("Noget gik galt. Sikker på at dit input er korrekt?");
        }
        System.out.println("point 11");
        String send6 = "";
        try {
            send6 = new String(Files.readAllBytes(Paths.get("C:\\Users\\nvigg\\Desktop\\Github\\Studieprojekt\\SemsesterPro1V2\\xoutput3.txt")));
        } catch (IOException e) {
          e.printStackTrace();
        } System.out.println("Test to see what is in the text file");
        System.out.println(send6);     

        String Send1 = " X000 Y000 Z255";  // Første karakter skal være 0. Smart. ;)
        String Send2 = " X000 Y000 Z255 X000 Y001 Z255";
        String Send3 = " X000 Y000 Z255 X000 Y001 Z255 X000 Y002 Z255";
        String Send4 = " X000 Y000 Z255 X000 Y001 Z255 X000 Y002 Z255 X000 Y003 Z255";
        String Send5 = " X000 Y000 Z255 X000 Y001 Z255 X000 Y002 Z255 X000 Y003 Z255 X000 Y004 Z255";
        String Send6 = send6;
        String Send10 = " X000 Y000 Z255 X000 Y001 Z255 X000 Y002 Z255 X000 Y003 Z255 X000 Y004 Z255 X000 Y005 Z255 X000 Y006 Z255 X000 Y007 Z255 X000 Y008 Z255 X000 Y009 Z255 X000 Y010 Z255 X000 Y011 Z255 X000 Y012 Z255 X000 Y013 Z255 X000 Y014 Z255 X000 Y015 Z255 X000 Y016 Z255 X000 Y017 Z255 X000 Y018 Z255 X000 Y019 Z255 X000 Y020 Z255 X000 Y021 Z255 X000 Y022 Z255 X000 Y023 Z255 X000 Y024 Z255 X000 Y025 Z255 X000 Y026 Z255 X000 Y027 Z255 X000 Y028 Z255 X000 Y029 Z255 X000 Y030 Z255 X000 Y031 Z255 X000 Y032 Z255 X000 Y033 Z255 X000 Y034 Z255 X000 Y035 Z255 X000 Y036 Z255 X000 Y037 Z255 X000 Y038 Z255 X000 Y039 Z255 X000 Y040 Z255 X000 Y041 Z255 X000 Y042 Z255 X000 Y043 Z255 X000 Y044 Z255 X000 Y045 Z255 X000 Y046 Z255 X000 Y047 Z255 X000 Y048 Z255 X000 Y049 Z255 X000 Y050 Z255 X000 Y051 Z255 X000 Y052 Z255 X000 Y053 Z255 X000 Y054 Z255 X000 Y055 Z255 X000 Y056 Z255 X000 Y057 Z255 X000 Y058 Z255 X000 Y059 Z255 X000 Y060 Z255 X000 Y061 Z255 X000 Y062 Z255 X000 Y063 Z255 X000 Y064 Z255 X000 Y065 Z255 X000 Y066 Z255 X000 Y067 Z255 X000 Y068 Z255 X000 Y069 Z255 X000 Y070 Z255 X000 Y071 Z255 X000 Y072 Z255 X000 Y073 Z255 X000 Y074 Z255 X000 Y075 Z255 X000 Y076 Z255 X000 Y077 Z255 X000 Y078 Z255 X000 Y079 Z255 X000 Y080 Z255 X000 Y081 Z255 X000 Y082 Z255 X000 Y083 Z255 X000 Y084 Z255 X000 Y085 Z255 X000 Y086 Z255 X000 Y087 Z255 X000 Y088 Z255 X000 Y089 Z255 X000 Y090 Z255 X000 Y091 Z255 X000 Y092 Z255 X000 Y093 Z255 X000 Y094 Z255 X000 Y095 Z255 X000 Y096 Z255 X000 Y097 Z255 X000 Y098 Z255 X000 Y099 Z255";
        switch (senddata){
            case 1:
                System.out.println("Sender følgende string: "+Send1+"\n");
                robotClient.write(Send1);
                break;
            case 2:
                System.out.println("Sender følgende string: "+Send2+"\n");
                robotClient.write(Send2);
                break;
            case 3:
                System.out.println("Sender følgende string: "+Send3+"\n");
                robotClient.write(Send3);
                break;
            case 4:
                System.out.println("Sender følgende string: "+Send4+"\n");
                robotClient.write(Send4);
                break;
            case 5:
                System.out.println("Sender følgende string: "+Send5+"\n");
                robotClient.write(Send5);
                break;
            case 6:
                System.out.println("Sender følgende string: "+Send6+"\n");
                robotClient.write(Send6);
                break;
            case 10:
                System.out.println("Well, du bad om det. Sender : "+Send10+"\n");
                robotClient.write(Send10);
                break;

        }
        System.out.println("Besked sendt. Lukker socket, starter forfra. \n");
        System.out.println(robStatus +" Og faktisk status: "+ robotClient.isConnected());
        robotClient.disconnect();
        //System.out.println("Connected to PLC? :" + robotClient.isConnected());
    }

}
