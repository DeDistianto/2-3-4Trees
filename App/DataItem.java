package App;
public class DataItem {
   
   public double dData;                                 // one data item
// -------------------------------------------------------------
   public DataItem(double dd)                           // constructor
      { dData = dd; }
// -------------------------------------------------------------
   public void displayItem()                            // menampilkan item, format "/27"
      { System.out.print("/"+dData); 
   }  
}
