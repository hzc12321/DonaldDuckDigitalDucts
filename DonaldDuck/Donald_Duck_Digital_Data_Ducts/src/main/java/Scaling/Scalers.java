package Scaling;

import static Main.TesterDonald.group11;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Scalers extends ConnectorPart4{
    public static String filepath5;
    
    public static void ReadFile5(){
        System.out.println("Enter file name that u want to use: ");
        filepath5=group11.next();
        System.out.println("");
    }
    public static void Scalling(){
        
        try{
            System.out.println("");
            ReadFile5();
            System.out.println("Please enter data from which cloumn: ");
            Scanner sc= new Scanner(System.in);
            String header = sc.nextLine();
            File a = new File (filepath5);
            Scanner sc2 = new Scanner (a);
            String s1 = sc2.nextLine();
            String []s1_split = s1.split(",");
            int column = 0;  
            
            for(int i = 0; i<s1_split.length; i++){ //determine the index for column where data is used
               if(s1_split[i].equalsIgnoreCase(header)){ 
                   column = i; 
               }
            }
            int row = 0;
            while (sc2.hasNextLine()){
                String test = sc2.nextLine();
                row ++; 
            }
        
            double[] data = new double [row];
            Scanner sc3 = new Scanner(a);
            String test2 = sc3.nextLine(); // scan the first row (header) since it is not used in calculation
            int k = 0;
            while (sc3.hasNextLine()){ //store data from single column into 1-D array for calculations 
                String s2 = sc3.nextLine();
                String [] s2_split = s2.split(",");
                double value = Double.parseDouble(s2_split[column]); // convert the string to double
                data[k] = value; k++;
            }
            System.out.println("\nThe values of " + header + " are: ");      
            int count = 0;
            for (double value : data){ 
                if (count%10 == 0) System.out.println(""); 
                count++; // to move output to new line when elements in a line reach 10
                System.out.printf("%-15.2f" , value);
            } 
            System.out.println("\n\n");
            System.out.println("What do you wish to do with the data (1 for standard scaling/ 2 for min max scaling): ");
            int key = sc.nextInt();
            PrintWriter pw = new PrintWriter(new FileOutputStream("scalers.csv"));

            // write the scale value into a csv file
            double sum = 0; double sumofsquare = 0;
            if (key == 1){ //perform standard scaling
                pw.println(header + "," + "Standard scaling");// write header (first row)into csv
                pw.println(header + ": ");
                for(int i = 0; i<row; i++) {
                   sum += data[i];
                   double square = data[i]*data[i];
                   sumofsquare += square;
                }
                   double squareofsum = sum*sum;
                   double answer = ((sumofsquare*row)-squareofsum)/(row*(row-1));
                   double standard_deviation = Math.pow(answer, 0.5);
                   double average = sum/row;
                   System.out.print("<" + header + ">");
                   System.out.println("\nThe values after standard scaling: ");
                   count = 0;
                   for(int i = 0; i<row; i++) {
                   if (count%10 == 0){System.out.println("");}
                   count++;
                   double standard = (data[i]-average)/standard_deviation;
                   System.out.printf("%-15f", standard);
                   pw.printf("%.2f , %f \n" ,data[i],standard); //write original value and value after scalling into csv
                }
            }else if(key == 2){ //perform minmax scaling
                pw.println(header + ","  +"MinMax scaling");
                double min = data[0]; 
                double max = data[0]; 
                for(int i = 0; i<row; i++){
                    if(data[i]>max){
                        max = data[i];
                    }
                    if (data[i]<min) {
                        min = data[i];
                    }
                } 
                double range = max - min;
                System.out.print("<" + header + ">");
                System.out.println("\nThe values after min max scaling: ");
                count = 0;
                for (int i = 0; i<row; i++){
                    if (count%10 == 0){ System.out.println("");}
                    count++; // move output to new line when elements in a row reach 10
                    double minmax = (data[i]-min)/range;
                    System.out.printf("%-15f" , minmax);
                    pw.printf("%.2f , %f \n" ,data[i],minmax); //write original value and value after scalling into csv
                }
            }else 
                System.out.println("invalid key!"); 
            System.out.println("");
            pw.close();
        } catch (FileNotFoundException e){
            System.out.println("error in file input");
        }
        
    }
}
