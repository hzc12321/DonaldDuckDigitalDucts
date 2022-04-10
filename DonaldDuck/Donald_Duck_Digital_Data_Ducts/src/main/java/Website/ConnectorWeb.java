package Website;

import java.io.IOException;


public class ConnectorWeb {
    
    public void DisplayImage(){
        ImageScraper is = new ImageScraper();
        is.PicScraper();
    }
    
    public void DisplayWebsiteData() throws IOException{
        DataScraper ds = new DataScraper();
        ds.InfoScraper();
    }
    
}
