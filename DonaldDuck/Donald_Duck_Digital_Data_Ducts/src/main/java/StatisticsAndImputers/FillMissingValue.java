package StatisticsAndImputers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.json.simple.JSONArray;

public class FillMissingValue extends ConnectorPart3{
    private static String filepath;
    private static Scanner group11 = new Scanner(System.in);
    
    private static void ReadCsv(){
        
        System.out.println("Enter file name that you want to use: ");
        filepath=group11.next();
        System.out.println("");
    }
    
    public static void FillValue() throws FileNotFoundException{
        ReadCsv();
        try{
        File a = new File(filepath);
        Scanner sc1 = new Scanner (a);
        int column = 0; int row  = 0;
        while (sc1.hasNextLine()){ // scan csv first time to get number of row and column for 2D array
            String s1 = sc1.nextLine();
            String [] s1_spilt = s1.split(",");
            column = s1_spilt.length;
            row ++;
        }
                     
        Scanner sc2 = new Scanner(a); 
        String [][] data = new String [row][column];
        for(int i = 0; i<row ; i++){ //scan and store data from csv into 2d array
            String s1 = sc2.nextLine();
            s1 += ", ,";
            String [] s1_split = s1.trim().split(",");
            for(int j = 0; j<column; j++){
                data[i][j] = s1_split[j];
                System.out.printf("%-30s" , s1_split[j]);
            }
            System.out.println("");
        }   
        System.out.println("\n\n");     
       
        Scanner sc4 = new Scanner (System.in);
        
        for (int i = 1; i<row ; i++){
            for (int j = 0; j<column; j++){
                if (data [i][j].isBlank() ){ //detect null values from 2d array
                    System.out.println("Blank space found in column " + data[0][j] + " at row " + i);
                    System.out.println("Please enter value: ");
                    data[i][j] = sc4.nextLine(); //store new input value into 2d array earlier
                    for (int k = 0; k<column; k++){ 
                        System.out.printf("%-30s", data[i][k]);
                    }
                    System.out.println("\n\n");;
                }
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("                                        There is no missing value                                          ");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        PrintWriter pw = new PrintWriter(a);
        for(int i = 0; i<row ; i++){ //write the updated 2D array back into csv file
            for(int j = 0; j<column ; j++){
                System.out.printf("%-30s", data[i][j]);
                pw.print(data[i][j] + ",");
            }
            System.out.println(""); 
            pw.println();
        }
        pw.close();
        
        System.out.println("Do you want to generate a JSON file for the processed data?");
        System.out.println(" If YES press (1) If don't want press anything to proceed.");
        int json=group11.nextInt();
       
        if(json==1){
            JSONArray jsonArray = new JSONArray();
            for (String[] w : data) {
                JSONArray arr = new JSONArray();
                for (String v : w) {
                 arr.add(v); // or some other conversion
                }
                jsonArray.add(arr);
            }
            try (FileWriter jsonout = new FileWriter("file.json")) {
 
            jsonout.write(jsonArray.toJSONString());
            jsonout.flush();
 
            } catch (IOException e) {
                 e.printStackTrace();
            }
            
            System.out.println("Generating (file).json ......");
            System.out.println("(file).json done!");
            System.out.println("Please check your file for more information.");
        }else{
        }
        
        }catch(FileNotFoundException e){
            System.out.println("File not found!!");
            System.out.println("");
        }
    }
}
