package Manipulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class SearchName extends ConnectorPart2{
     public static void Searchdata(){
        Scanner group11 = new Scanner(System.in);
        
        System.out.println("Enter file name: ");
        String SearchFile = group11.nextLine();
        
        File filename = new File (SearchFile);
        System.out.println("Enter the name of person you wish to search: ");
        String name = group11.nextLine();
        
            try { 
                Scanner csv= new Scanner (filename);
                int column = 0; 
                int row  = 0;
                
                while (csv.hasNextLine()){ //scan csv first time to get number of rows and columns for 2D array
                    String s1 = csv.nextLine();
                    String [] s1_spilt = s1.split(",");
                    column = s1_spilt.length;
                    row++;
                }
            
                Scanner csv2= new Scanner(filename); // scan csv file second time to store data into 2D array
                String[][] data = new String [row][column];
                  int index = 0;
                for(int i=0;i<row;i++){
                    String s1 = csv2.nextLine();
                    s1 += ", ,";
                    String [] s1_split = s1.split(",");
                  
                    for (int j=0;j<column;j++){
                        if (s1_split[j].equalsIgnoreCase(name)) {
                            index = i; //determine the name is at which row
                        }  
                        data[i][j] = s1_split[j];}
                    }
                if (index>0){ //to check whether name exist
                   PrintWriter pw = new PrintWriter(new FileOutputStream("SearchName.csv"));
                System.out.println(name + " found at row " + (index+1));
                int i = 0;                                                                              
                for (int k = 0; k<2 ; k++){ // Loop twice to print out header (first row) and row with searched name
                for (int j = 0; j<column; j++){
                    System.out.printf("%-30s", data[i][j]);
                    pw.print(data[i][j] + ",");} //write to a new csv file
                i = index;
                System.out.println(""); pw.println();}
                pw.close();
                
                }else{
                    System.out.println("Name could not be found");
                }
               
            }catch (FileNotFoundException e){
                System.out.println("File Not Found.");
            } 
    }

}
