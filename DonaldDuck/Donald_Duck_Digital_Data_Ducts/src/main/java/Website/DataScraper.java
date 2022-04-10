package Website;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataScraper extends ConnectorWeb{
    //https://computersciencestudentsalary.blogspot.com/2020/12/2020-worlds-richest-people-ranking.html
    //https://computersciencestudentsalary.blogspot.com/2020/12/age-table.html
    //https://computersciencestudentsalary.blogspot.com/2020/12/multiplication-time-table-until-4.html
    public static void InfoScraper() throws IOException{
        Scanner group11 = new Scanner(System.in);

        System.out.println("Enter url: ");
        String url = group11.next();
        System.out.println("");
        
        System.out.println("Enter file name to store the data from website:");
        String fileName = group11.next();
        FileWriter writer = new FileWriter(fileName);
        
        try {
            System.out.println("Extracting data from URl....");
            System.out.println("");
            Document doc = Jsoup.connect(url).get();
            Element tableElement = doc.select("table").first();

            Elements tableHeaderEles = tableElement.select("tr");
            for (int i = 0; i < tableHeaderEles.size(); i++) {
                System.out.println(tableHeaderEles.get(i).text());
            }
        
            Elements tableRowElements = tableElement.select(":not(thead) tr");

            for (int i = 0; i < tableRowElements.size(); i++) {
                Element row = tableRowElements.get(i);
                Elements rowItems = row.select("td");
                for (int j = 0; j < rowItems.size(); j++) {
                
                    writer.append(rowItems.get(j).text());
                
                    if(j != rowItems.size() ){
                        writer.append(',');
                    }
                }
                 writer.append('\n');
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("Error with file output");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
