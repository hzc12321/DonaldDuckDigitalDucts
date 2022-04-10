package Manipulation;

import static DataFrame.SaveDataframeToCsvFile.ReadCsv;
import static Manipulation.ConcatenateDataFrame.ReadFile3;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class DataSorting extends ConnectorPart2{
    
    public static void Sorting(){
        Scanner group11 = new Scanner(System.in);
        System.out.println("Enter file: ");
        String ffile = group11.next();
        int column = 0; 
        int row  = 0;
        String[][]data=null;
        try {
            Scanner csv= new Scanner(new FileInputStream(ffile));
            
            while (csv.hasNextLine()){
                String s1 = csv.nextLine();
                String [] s1_spilt = s1.split(",");
                column = s1_spilt.length;
                row ++;
            }
            
            Scanner csv2= new Scanner(new FileInputStream(ffile)); 
            data = new String [row][column];
            //System.out.println(row+" "+column);
            for (int i=0;i<row;i++){
                String s1 = csv2.nextLine();
                String [] s1_split = s1.split(",");
                for (int j = 0; j<column; j++){
                    data[i][j] = s1_split[j];
                    System.out.printf("%-30s", s1_split[j]);
                }
                 System.out.println("");
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found!!");
        }
        //Method c starts here
        int sortindex=-1;
        int ascordsc=0;
        Scanner sc=new Scanner(System.in);
        String[] x=data[0];// read the first row of data(the name of the columns) to array x
        boolean wronginput=false;
        int columnindex=-1;
        System.out.println("");
        while(sortindex<0){//this loop can get which column is used as the parameter for sorting
            if(wronginput==true){
                System.out.println("Invalid input, please enter again.");}
            System.out.println("Enter the name of the column to be used as comparism to sort: ");
            String sortcolumn=sc.nextLine();
            int samenamecolumn=0;
            for(int i=0;i<x.length;i++){
                if(sortcolumn.compareToIgnoreCase(x[i])==0&&samenamecolumn<2){
                    sortindex=i;
                    samenamecolumn++;
                }
                if(samenamecolumn>1){
                    while(columnindex<0){
                        System.out.println("There are same name columms, the name cannot be used to identify anymore, please enter the number of the column instead(column counted from the left, starting from 1): ");
                        columnindex=sc.nextInt();
                        sortindex=columnindex-1;}
                }
            }
            if(sortindex<0)
                wronginput=true;
        }
        

        while(ascordsc==0){// this loop get the clear instruction from user whether to sort ascending or descending
            System.out.println("");
            System.out.println("Ascending order or descending order? Enter any positive value for ascending sort, negative value for descending sort : ");
            ascordsc=sc.nextInt();
            if(ascordsc==0){
                System.out.println("Invalid input, please key in again.");}
                
        }
        System.out.println("");
        /*if(IsInt(data[1][sortindex])==true){//use the first element in the column to check whether the whole column is int
           int[] TempArray=new int[row-1];
           int y= TempArray.length;
           String[] TempArray2= new String[column];
           for(int i=0;i<y;i++){
                TempArray[i]=Integer.parseInt(data[i+1][sortindex]);// file[i+1] is to skip first row which are column names, hence index 0 at TempArray saves index 1 of file etc.
            } 
           if(ascordsc>0){
                for (int i = 1; i < y; i++){
                    for (int j = 0; j < y-1; j++) {
                        if (TempArray[j] > TempArray[j+1]){
                            //swap between rows in file
                            TempArray2= data[j+1];//j+1 because first row of column names already skipped, things at TempArray[0] represents the row at file[1][]
                            data[j+1]=data[j+2];
                            data[j+2]=TempArray2;
                            //swap between values of the column being used as sorting criteria that has been copied into 1D array TempArray 
                            int temp = TempArray[j]; 
                            TempArray[j] = TempArray[j+1]; 
                            TempArray[j+1] = temp; 
                        }
                    }
                }
           }
           else if(ascordsc<0){
               for (int i = 1; i < y; i++){
                    for (int j = 0; j < y-1; j++) {
                        if (TempArray[j] < TempArray[j+1]){
                            TempArray2= data[j+1];//j+1 because first row of column names already skipped, things at TempArray[0] represents the row at file[1][]
                            data[j+1]=data[j+2];
                            data[j+2]=TempArray2;                            
                            int temp = TempArray[j]; 
                            TempArray[j] = TempArray[j+1]; 
                            TempArray[j+1] = temp; 
                        }
                    }
                }
           }
        }*/
       
        if(IsFloat(data[1][sortindex])==true){//use the first element in the column to check whether the whole column is float
           double[] TempArray=new double[row-1];
           int y= TempArray.length;
           String[] TempArray2= new String[column];
           for(int i=0;i<y;i++){
                TempArray[i]=Float.parseFloat(data[i+1][sortindex]);// file[i+1] is to skip first row which are column names, hence index 0 at TempArray saves index 1 of file etc.
            } 
            if(ascordsc>0){
                for (int i = 1; i < y; i++){
                    for (int j = 0; j < y-1; j++) {
                       if (TempArray[j] > TempArray[j+1]){
                            //swap between rows in file
                            TempArray2= data[j+1];//j+1 because first row of column names already skipped, things at TempArray[0] represents the row at file[1][]
                            data[j+1]=data[j+2];
                            data[j+2]=TempArray2;
                            //swap between values of the column being used as sorting criteria that has been copied into 1D array TempArray 
                            double temp = TempArray[j]; 
                            TempArray[j] = TempArray[j+1]; 
                            TempArray[j+1] = temp; 
                        }
                    }
                }
           }
           else if(ascordsc<0){
               for (int i = 1; i < y; i++){
                    for (int j = 0; j < y-1; j++) {
                        if (TempArray[j] < TempArray[j+1]){
                            TempArray2= data[j+1];//j+1 because first row of column names already skipped, things at TempArray[0] represents the row at file[1][]
                            data[j+1]=data[j+2];
                            data[j+2]=TempArray2;
                            double temp = TempArray[j]; 
                            TempArray[j] = TempArray[j+1]; 
                            TempArray[j+1] = temp; 
                        }
                    }
                }
           }
        }
        else{//if not int or double, then sort as String
           String[] TempArray=new String[row-1];
           int y= TempArray.length;
           String[] TempArray2= new String[column];
           for(int i=0;i<y;i++){
                TempArray[i]=(data[i+1][sortindex]);// file[i+1] is to skip first row which are column names, hence index 0 at TempArray saves index 1 of file etc.
            } 
            if(ascordsc>0){
                for (int i = 1; i < y; i++){
                    for (int j = 0; j < y-1; j++) {
                        if (TempArray[j].compareToIgnoreCase(TempArray[j+1])>0){
                            //swap between rows in file
                            TempArray2= data[j+1];//j+1 because first row of column names already skipped, things at TempArray[0] represents the row at file[1][]
                            data[j+1]=data[j+2];
                            data[j+2]=TempArray2;
                            //swap between values of the column being used as sorting criteria that has been copied into 1D array TempArray 
                            String temp = TempArray[j]; 
                            TempArray[j] = TempArray[j+1]; 
                            TempArray[j+1] = temp; 
                        }
                    }
                }
           }
           else if(ascordsc<0){
               for (int i = 1; i < y; i++){
                    for (int j = 0; j < y-1; j++) {
                        if (TempArray[j].compareToIgnoreCase(TempArray[j+1])<0){
                            TempArray2= data[j+1];//j+1 because first row of column names already skipped, things at TempArray[0] represents the row at file[1][]
                            data[j+1]=data[j+2];
                            data[j+2]=TempArray2;
                            String temp = TempArray[j]; 
                            TempArray[j] = TempArray[j+1]; 
                            TempArray[j+1] = temp; 
                        }
                    }
                }
           }
            //string sort
        }
        
        System.out.println("File after sorting: ");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                System.out.printf("%-30s", data[i][j]);
            }
                System.out.println("");            
        }
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("SortedData.csv"));//overwrite the original file
            for (int i = 0; i <data.length; i++) {
                for(int j=0;j<data[0].length;j++){
                    out.print(data[i][j]);
                    if(j!=data[0].length-1){
                        out.print(",");
                    }
                }
                out.println();
            }
            out.close();
            System.out.println("Sorted data saved successfully into file SortedData.csv !");
        } catch (IOException e) {
            System.out.println("problem writing to the file");
        }
    }
    public static boolean IsInt(String x){ 
        try {  
            Integer.parseInt(x);  
            return true;}
        catch(NumberFormatException e){  
            return false; 
        }
    }
    
    public static boolean IsFloat(String x){ 
        try {  
            Float.parseFloat(x);  
            return true;}
        catch(NumberFormatException e){  
            return false;  
            }
    }
}
