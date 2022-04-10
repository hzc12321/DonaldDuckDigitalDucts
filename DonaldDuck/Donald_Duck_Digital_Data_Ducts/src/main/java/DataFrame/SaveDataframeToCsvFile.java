package DataFrame;

import static Main.TesterDonald.group11;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveDataframeToCsvFile {
    public static String filepath;
    public static void ReadCsv(){
        
        System.out.println("Enter file name that you want to use: ");
        filepath=group11.next();
        System.out.println("");
        
    }
    public static void UserInputData() throws IOException{
        System.out.println("");
        System.out.println("How many row of data you want to key in?");
        System.out.println("Enter value: ");
        int NumOfRow = group11.nextInt();
        System.out.println("");
        System.out.println("How many column of data you want to key in?");
        System.out.println("Enter value: ");
        int NumOfColumn = group11.nextInt();
        
        String[][] data = new String[NumOfRow][NumOfColumn];
        
        for (int i=0;i<NumOfRow;i++){
            for (int j=0;j<NumOfColumn;j++){
                System.out.println("Enter value for row "+(i+1)+" column " +(j+1) +" : " );
                data[i][j] = group11.next();
            }
        }
        
        try{
            System.out.println("");
            ReadCsv();
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println("                                        Adding new data.........                                                    ");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            FileWriter fw = new FileWriter(filepath,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i=0;i<NumOfRow;i++){
                for (int j=0;j<NumOfColumn;j++){
                    System.out.printf("%-30s", data[i][j]); 
                    pw.print(data[i][j] + ",");
                }
                System.out.println("");
                pw.println();               
            }
        pw.flush();
        pw.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        
    }
}
