package DataFrame;

import java.io.IOException;

public class ConnectorPart1 {
    
    public void DisplayPart1a(){
        ConstructDataframeFromCsvFile cd = new ConstructDataframeFromCsvFile();
        cd.ScanDataFromFile();
    }
    
    public void DisplayPart1b() throws IOException{
        SaveDataframeToCsvFile sd = new SaveDataframeToCsvFile();
        sd.UserInputData();
    }
}
