package ExtraFeature;

import static DataFrame.SaveDataframeToCsvFile.ReadCsv;
import static DataFrame.SaveDataframeToCsvFile.filepath;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonFileConvertor {
    public static void PrintJsonFile(){
        
        Scanner group11 = new Scanner(System.in);
        int column = 0; 
        int row  = 0;
        String[][]file=null;
        
        ReadCsv();
        try {
            Scanner csv= new Scanner(new FileInputStream(filepath));
            
            while (csv.hasNextLine()){
                String s1 = csv.nextLine();
                String [] s1_spilt = s1.split(",");
                column = s1_spilt.length;
                row ++;
            }
            
            Scanner csv2= new Scanner(new FileInputStream(filepath)); 
            file = new String [row][column];
            for (int i=0;i<row;i++){
                String s1 = csv2.nextLine();
                String [] s1_split = s1.split(",");
                for (int j = 0; j<column; j++){
                    file[i][j] = s1_split[j];
                    System.out.printf("%-30s", s1_split[j]);
                }
                 System.out.println("");
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found!!");
        }
        String[][] file2=new String[column][row-1];
        for(int i=1;i<file.length;i++){
            for(int j=0;j<file[0].length;j++){
                file2[j][i-1]=file[i][j];
            }
        }
        JSONObject jsonObject = new JSONObject();      
        for (int i=0;i<file2.length;i++) {
            JSONArray jsonArray=new JSONArray();
            for(int j=0;j<file2[i].length;j++){
                jsonArray.add(file2[i][j]);
            }
            jsonObject.put(file[0][i],jsonArray);
        }
        try (FileWriter jsonout = new FileWriter(filepath.substring(0,filepath.length()-3)+"json")) {
 
            jsonout.write(jsonObject.toJSONString());
            jsonout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(filepath.substring(0,filepath.length()-3)+"json is generating....");
        try{
           Thread.sleep(1500);
        }catch(Exception e) {
            System.out.println(e);
        }         
        System.out.println(filepath.substring(0,filepath.length()-3)+"json done. Please check ur files for more information."); 
    }
}
