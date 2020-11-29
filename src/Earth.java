package src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

public class Earth extends JComponent{

    public double[][] arrayOfEarth;
    public Map<double[], Double> mapOfEarth;
    public static  BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);

    public void readDataArray(String fileName) throws IOException {

        Scanner file = new Scanner(new File(fileName));
        int rows=2500000;
        int cols = 3;
        int total=0;
        arrayOfEarth = new double[rows][cols];
        while (file.hasNextLine()) {
            String[] line = file.nextLine().split("\t");
            arrayOfEarth[total][0] = Double.parseDouble(line[0]);
            arrayOfEarth[total][1] = Double.parseDouble(line[1]);
            arrayOfEarth[total][2] = Double.parseDouble(line[2]);
            total++;
        }
        file.close();
    }

    public List<double[]> coordinatesAbove(double altitude) {
        List<double[]> above = new ArrayList<>();
        for (int i = 0; i < arrayOfEarth.length; i++) {
            if (arrayOfEarth[i][2] >= altitude) {
                above.add(arrayOfEarth[i]);
            }
        }
        return above;
    }

    public List<double[]> coordinatesBelow(double altitude) {
        List<double[]> below = new ArrayList<>();
        for (int i = 0; i <  arrayOfEarth.length; i++) {
            if (arrayOfEarth[i][2] <= altitude) {
                below.add(arrayOfEarth[i]);
            }
        }
        return below;
    }

    public void percentageAbove(double altitude) {
        double result;
        result = coordinatesAbove(altitude).size();
        result = result /  arrayOfEarth.length * 100;
        System.out.printf("Percentage  of coordinates above %.1f meters:%.1f%% %n", altitude, result);
    }

    public void percentageBelow(double altitude) {
        double result;
        result = coordinatesBelow(altitude).size();
        result = result /  arrayOfEarth.length * 100;
        System.out.printf("Percentage  of coordinates below %.1f meters:%.1f%% %n", altitude, result);
    }

    public void userPercentageAbove() {
        double result;
        boolean check = true;
        Scanner in = new Scanner(System.in);
        outer:
        while (check) {
            try{
            System.out.println("Please enter an altitude or \"quit\" to end program");
            String usin = in.nextLine();
            if (usin.equalsIgnoreCase("quit")) {
                System.out.println("Bye!");
                break;
            } else {
                double d = Double.parseDouble(usin);
                List<double[]> above = new ArrayList<>();
                for (int i = 0; i <  arrayOfEarth.length; i++) {
                    if (arrayOfEarth[i][2] >= d) {
                        above.add(arrayOfEarth[i]);
                    }
                }
                result = above.size();
                result = result /  arrayOfEarth.length * 100;
                System.out.printf("Percentage  of coordinates above %.1f meters:%.1f%% %n", d, result);
                continue outer;
            }
            }
            catch (NumberFormatException e) {
                    System.out.println("input is not a valid number");
                    continue outer;
                }
        }
    }

    public void readDataMap(String fileName) throws FileNotFoundException {
        Scanner file = new Scanner(new File(fileName));
        mapOfEarth = new HashMap<double[], Double>();
        double[] longlat;
        while (file.hasNextLine()) {
            String[] line = file.nextLine().split("\t");
            longlat = new double[]{Double.parseDouble(line[0]), Double.parseDouble(line[1])};
            double alt = Double.parseDouble(line[2]);
            mapOfEarth.put(longlat, alt);
            //System.out.println(longlat[0]+" "+longlat[1]);
        }
    }

        public void generateMap(double resolution){
            Map<Integer, Double> randomMap;
            randomMap = new HashMap<Integer, Double>();
            int nr=0;
            int c=0;
            Random rnd = new Random();
            double randalt=0;
            if (resolution==1){
                nr=360*180;
                while(c<nr){
                    randalt=rnd.nextDouble();
                    randomMap.put(c,randalt);
                    c++;
                }
            }
            else if (resolution==0.5){
                nr=720*360;
                while(c<nr){
                    randalt=rnd.nextDouble();
                    randomMap.put(c,randalt);
                    c++;
                }
            }
            else if (resolution==2){
                nr=180*90;
                while(c<nr){
                    randalt=rnd.nextDouble();
                    randomMap.put(c,randalt);
                    c++;
                }
            }
            else{
                System.out.println("No such resolution");
            }
        }

   public double getAltitude(double longitude, double latitude){
       double altitude;
       for (double[] name: mapOfEarth.keySet()){
           double[] key = name;
           String value = mapOfEarth.get(name).toString();
           //key[0]=long  key[1]=lat  value= alt
           if (longitude==key[0] && latitude==key[1]){
               System.out.println("Your entered longitude: "+longitude+" and latitude: "+ latitude+" coordinates are in this altitude: "+ value);
           }
       }
       return 0;
   }

    public void findAltD() {
        boolean check = true;
        double alti=0;
        Scanner in = new Scanner(System.in);
        outer:
        while (check) {
            System.out.println("Please enter a longitude (0 - 360) and latitude (-90 - 90) like this (50-45)  or \"quit\" to end program");
            String usin = in.next();
            if (usin.equalsIgnoreCase("quit")) {
                System.out.println("Bye!");
                break;
            } else {
                try {
                    String[] line = usin.split("-", 2);
                    double longi = Double.parseDouble(line[0]);
                    double lati = Double.parseDouble(line[1]);
                    ///System.out.println(line);
                    System.out.println("Your coordinates: " + longi + " " + lati);
                    List<double[]> above = new ArrayList<>();
                    if (longi >= 0 && longi <= 360 || lati >= -90 || lati <= 90) {
                        for (int i = 0; i <  arrayOfEarth.length; i++) {
                            if (arrayOfEarth[i][0] == longi && arrayOfEarth[i][1] == lati) {
                                alti = arrayOfEarth[i][2];
                            }
                        }
                        System.out.println("Altitude  of Longitude: " + longi + " and Latitude: " + lati + "is : " + alti);
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("input is not a valid number");
                }
                continue outer;
            }
        }
    }

}

