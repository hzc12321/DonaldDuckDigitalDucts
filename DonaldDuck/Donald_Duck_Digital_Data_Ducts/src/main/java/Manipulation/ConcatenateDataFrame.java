package Manipulation;

import static Main.TesterDonald.group11;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConcatenateDataFrame extends ConnectorPart2{
    public static String filepath1;
    public static String filepath2;
    
    public static void ReadFile2(){
        System.out.println("Enter main file: ");
        filepath1=group11.next();
        System.out.println("");
    }
    
    public static void ReadFile3(){
        System.out.println("Enter sub-file to concatenate with main file: ");
        filepath2=group11.next();
    }
    
    public static void ConcatenateData(){
        System.out.println("");
        try{
            Scanner csv1= new Scanner(new FileInputStream(filepath1));
            int column1=0; 
            int row1=0;
            
            while (csv1.hasNextLine()){ // scan csv first time to get number of row and columns for 2d array
                String s1 = csv1.nextLine();
                String [] s1_spilt = s1.split(",");
                column1=s1_spilt.length;
                row1++;
            }
            
            Scanner csv2= new Scanner(new FileInputStream(filepath2));
            int column2=0; 
            int row2=0;
            
            while (csv2.hasNextLine()){ // scan csv first time to get number of row and columns for 2d array
                String s2 = csv2.nextLine();
                String [] s2_spilt = s2.trim().split(",");
                column2 = s2_spilt.length;
                row2 ++;
            }  
               
            Scanner csv3= new Scanner(new FileInputStream(filepath1));
            String [][] data1 = new String [row1][column1]; // scan csv second time to store data into 2d array
            for (int i = 0; i<row1 ; i++){ //read from file 1
                String s1 = csv3.nextLine();
                String [] s1_split = s1.trim().split(",");
                for (int j = 0; j<column1; j++){
                    data1[i][j] = s1_split[j];
                }
            }
            Scanner csv4= new Scanner(new FileInputStream(filepath2));
            String [][] data2 = new String[row2][column2]; // read from file 2
            for (int i = 0; i<row2; i++){
                String s2 = csv4.nextLine();
                String [] s2_split = s2.trim().split(",");
                for (int j = 0; j<column2 ; j++){
                    data2[i][j] = s2_split[j];
                }
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            System.out.println("Data after combine......");
            PrintWriter pw = new PrintWriter(new FileOutputStream(filepath1),true);
            if (row1 == row2){ // check whether number of row is equal
                for (int i = 0; i<row1; i++ ){ 
                    int column = 0;
                    for (int j = 0; j<column1; j++){
                        System.out.printf("%-30s", data1[i][j]); //display and write the elements in first file
                        pw.print(data1[i][j] + ",");
                    } 
                    while(column<column2){ // append, display and write the elements in second file to the right of first file
                         System.out.printf("%-30s", data2[i][column]); 
                         pw.print(data2[i][column] + ",");
                         column++;
                    }
                    System.out.println(""); 
                    pw.println();
                } 
                pw.close();
            }else if (column1 == column2){ //check whether number of column is equal
                for (int i = 0; i<row1; i++ ){
                    for (int j = 0; j<column1; j++){ 
                        System.out.printf("%-30s", data1[i][j]); // display and write all elements into file 1
                        pw.print(data1[i][j] + ",");
                    }
                    System.out.println(""); 
                    pw.println();
                }
                for (int i = 1; i<row2; i++ ){ //start from 1 since we skip row 0 which is header
                    for (int j = 0; j<column2; j++){ // append, display and write elements in file 1 to the bottom of file1  
                        System.out.printf("%-30s", data2[i][j]);
                        pw.print(data2[i][j] + ",");
                    }
                    System.out.println(""); 
                    pw.println();
                }
                    pw.close();
            }else{
                System.out.println("Unequal number of column /row");
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found!!!");
        }
    }
}
