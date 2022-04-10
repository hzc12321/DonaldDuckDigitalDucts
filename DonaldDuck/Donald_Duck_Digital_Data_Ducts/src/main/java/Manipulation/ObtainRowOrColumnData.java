package Manipulation;

import static Main.TesterDonald.group11;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ObtainRowOrColumnData extends ConnectorPart2 {
    
    public static void ObtainRecordInRow(){
        File filename = new File ("DATA.csv");
        
        System.out.println("");
        System.out.println("Enter start number for range: "); // enter first and second row to be displayed
        int RowRange1=group11.nextInt();                                                    
        System.out.println("Enter end number for range: ");
        int RowRange2=group11.nextInt();
        System.out.println("");
        
        try {
            Scanner csv= new Scanner(new FileInputStream(filename)); 
            int column = 0; 
            int row  = 0; 
            
            while (csv.hasNextLine()){ //scan csv first time to get number of row and columns for 2d array
                String s1 = csv.nextLine();
                String [] s1_spilt = s1.split(",");
                column = s1_spilt.length;
                row ++;
            }
            
            Scanner csv2= new Scanner(new FileInputStream(filename)); 
            String [][] data = new String [row][column];
            for (int i=0;i<row;i++){ // // scan csv first time to get number of row and columns for 2d array
                String s1 = csv2.nextLine();
                String [] s1_split = s1.split(",");
                for (int j = 0; j<column; j++){
                    data[i][j] = s1_split[j];
                }
            } 
            PrintWriter pw = new PrintWriter(new FileOutputStream("ObtainRowOrColumn.csv"));//write data into new csv file
             for(int j=0; j<column; j++){ //display first row (header)
                System.out.printf("%-30s", data[0][j]);
                pw.print(data[0][j] + ",");
             }
            System.out.println("");
            pw.println();
            for (int j=0; j<column; j++){ //display first row enter by users
                System.out.printf("%-30s", data[RowRange1][j]);
                pw.print(data[RowRange1][j] + ",");
            }
            System.out.println("");
            pw.println();
            for (int i=RowRange2;i<row;i++){ //display second row enter by users and rows after 
               for(int j=0; j<column; j++){
                   System.out.printf("%-30s", data[i][j]);
                   pw.print(data[i][j] + ",");
               }
                System.out.println("");
                pw.println();
            }
            pw.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found!!");
        }
    }
    
    public static void ObtainRecordInColumn(){
        File filename = new File ("DATA.csv");
        
        Scanner group11 = new Scanner(System.in);
        System.out.println("Enter the number of column you want to display: ");
        String input = group11.nextLine(); // enter number of column to displayed
        
        int n = Integer.parseInt(input);
        
        String [] header = new String[n];
        for (int i = 0; i<n; i++){
            System.out.println("Enter name of column: "); 
            header[i] = group11.nextLine();
        }
      
         
        try{
            Scanner csv= new Scanner (filename);
            int column = 0; 
            int row  = 0;
                
            while (csv.hasNextLine()){ // scan csv first time to get number of row and columns for 2d array
                String s1 = csv.nextLine();
                String [] s1_spilt = s1.split(",");
                column = s1_spilt.length;
                row++;
            }
            
            Scanner csv2= new Scanner(filename); 
            String[][] data = new String [row][column];
                
            for(int i=0;i<row;i++){ // scan csv second time to store data into 2d array
                String s1 = csv2.nextLine();
                s1 += ", ,";
                String [] s1_split = s1.split(",");
                for (int j=0;j<column;j++){
                    data[i][j] = s1_split[j];
                } 
            }
            PrintWriter pw = new PrintWriter(new FileOutputStream("ObtainRowOrColumnData.csv"));
            int [] column_index = new int [n];
            for (int k = 0; k<n; k++){ //determine index of the column to be displayed
                for (int j = 0; j<column ; j++){
                    if (header[k].equalsIgnoreCase(data[0][j])){ 
                        column_index[k] = j; 
                    }
                }
            }
               int i = 0;
               while (i<row){ //display the column based on index 
                for ( int k = 0 ; k< n ;k++){
                    for (int j = 0; j<column; j++){
                        if (j == column_index[k]){
                            System.out.printf("%-30s", data [i][j]);
                            pw.print(data[i][j] + ","); //write the data into new csv file
                        }
                    } 
                } 
                System.out.println(""); 
                pw.println();
                i++;
                } pw .close();
            }catch(FileNotFoundException e){
            System.out.println("File not found!!");
            } 
    }
    
}
