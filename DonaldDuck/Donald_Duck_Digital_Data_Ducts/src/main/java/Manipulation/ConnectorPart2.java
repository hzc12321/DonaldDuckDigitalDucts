package Manipulation;

import java.io.IOException;

public class ConnectorPart2 {
    public void DisplayPart2a(){
        ConcatenateDataFrame cdf = new ConcatenateDataFrame();
        cdf.ReadFile2();
        cdf.ReadFile3();
        cdf.ConcatenateData();
    }
    
    public void DisplayPart2b1() throws IOException{
        ObtainRowOrColumnData od = new ObtainRowOrColumnData();
        od.ObtainRecordInColumn();
    }
    
    public void DisplayPart2b2() throws IOException{
        ObtainRowOrColumnData od = new ObtainRowOrColumnData();
        od.ObtainRecordInRow();
    }
    
    public void DisplayPart2c(){
        DataSorting ds = new DataSorting();
        ds.Sorting();
    }

    public void DisplayPart2d() throws IOException{
        RemoveDuplicatedRowInData rdr = new RemoveDuplicatedRowInData();
        rdr.DeleteDuplicatedRow();
    }
    
    public void DisplayPart2e() throws IOException{
        RemoveNullDataFrame rndf = new RemoveNullDataFrame();
        rndf.deleteRecord();
    }
    
    public void DisplayPart2Extra(){
        SearchName s = new SearchName();
        s.Searchdata();
    }

}
