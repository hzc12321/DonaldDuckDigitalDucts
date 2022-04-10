package Manipulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.json.simple.JSONArray;

public class RemoveNullDataFrame extends ConnectorPart2{
    
    public static void deleteRecord(){
        
        Scanner group11 = new Scanner(System.in);
        
        System.out.println("Enter file to be inputed: ");
        String file=group11.nextLine();
        
        System.out.println("Enter the number of null columns to be detected: "); //how many column to be taken into account
        String input =group11.nextLine();
        
        int n = Integer.parseInt(input);
        String [] header = new String[n];
        for(int i=0;i<n;i++){
            System.out.println("Enter the name of the column to be detected: "); 
            header[i]=group11.nextLine();
        }
   
        try{
            
            Scanner csv= new Scanner(new FileInputStream(file));
            int column=0; 
            int row=0;
        
            while(csv.hasNextLine()){ //scan csv first time to get number of rows and columns for 2d array
                  String s1=csv.nextLine();
                  String [] s1_spilt = s1.trim().split(",");
                  column = s1_spilt.length;
                  row ++;
            }
            
            Scanner csv2= new Scanner(new FileInputStream(file));
            String[][] data = new String [row][column];
            for(int i = 0; i<row ; i++){ //scan csv second time to store data into 2d array
                String s1 = csv2.nextLine();
                s1 += ", ,";
                String [] s1_split = s1.split(",");
                for(int j = 0; j<column; j++){
                    data [i][j] = s1_split[j];
                }
            } 
           
            int [] null_column_index = new int [n];
            
            for (int i = 0; i<n; i++){
                for (int j = 0; j<column; j++){
                    if (header[i].equalsIgnoreCase(data[0][j])){
                        null_column_index [i]= j; // find the column index for column name enter by user
                    }
                }
            }
            int remove_row = 0;
            stop :for (int i = 1; i<row; i++){
            int null_count = 0;
            for (int k = 0; k<n ; k++){
                for (int j = 0; j<column; j++){
                    if (data[i][j].isBlank() && j == null_column_index[k]){
                        null_count++; // if column is blank and is at column index
                    }
                }
            }
            if (null_count == n) { //check whether the number of null column is equal to number enter by user
                remove_row = i; // Assign the whole row to be removed later
                   break 
                   stop;
            }
            }
            
            PrintWriter pw = new PrintWriter (file); //rewrite and display the elements into csv file without a certain row
            for (int i = 0; i<row ; i++){
                if (i==remove_row) // skip this row to remove it
                    continue;
                for (int j = 0; j<column; j++){
                    System.out.printf("%-30s", data[i][j]);
                    pw.print(data[i][j] + ",");
                } System.out.println("");
                pw.println();
            } 
            pw.close();
            
            System.out.println("");
            System.out.println("Do you want to generate a JSON file for the processed data?");
            System.out.println("If yes press (1) If don't want press any thing to proceed.");
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
            System.out.println("(file).json is generating....");
            
            try{
                Thread.sleep(2000);
            }catch(Exception e) {
                System.out.println(e);
            }
            
            System.out.println("(file).json done. Please check ur files for more information.");
        }else{
        }
        
        }catch(FileNotFoundException e){
            System.out.println("File not found!!");
        }
        
    }    
    
}
