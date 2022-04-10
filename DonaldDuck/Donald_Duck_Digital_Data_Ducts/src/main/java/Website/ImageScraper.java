package Website;

import java.io.PrintWriter;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageScraper extends ConnectorWeb{
    public static void PicScraper(){
        Scanner group11 = new Scanner(System.in);
        
        System.out.println("Enter url of website: ");
        String url = group11.next();
        
        System.out.println("Enter file name to store the image url:");
        String fileImage = group11.next();
        System.out.println("");
        try {
            PrintWriter pw = new PrintWriter(fileImage);
            final Document document = Jsoup.connect(url).get();
            String title = document.title();
            System.out.println("Title: "+title);
            
            System.out.println("Getting all images...");
            Elements image = document.getElementsByTag("img");
            
            for(Element src: image){
                System.out.println(src.attr("abs:src"));
                pw.println(src.attr("abs:src"));
            }
            pw.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
