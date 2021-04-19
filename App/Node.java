package App;
public class Node {
    private static final int ORDER = 4;
    private int numItems;
    private Node parent;
    private Node childArray[] = new Node[ORDER];
    private DataItem itemArray[] = new DataItem[ORDER-1];
 // -------------------------------------------------------------
    public void connectChild(int childNum, Node child)  // nyambungkan child ke node
       {
       childArray[childNum] = child;
       if(child != null)
          child.parent = this;
       }
 // -------------------------------------------------------------
    public Node disconnectChild(int childNum)          // memutuskan child dari node
       {
       Node tempNode = childArray[childNum];
       childArray[childNum] = null;
       return tempNode;
       }
 // -------------------------------------------------------------
    public Node getChild(int childNum)
       { return childArray[childNum]; }
 // -------------------------------------------------------------
    public Node getParent()
       { return parent; }
 // -------------------------------------------------------------
    public boolean isLeaf()
       { return (childArray[0]==null) ? true : false; }
 // -------------------------------------------------------------
    public int getNumItems()
      { return numItems; }
 // -------------------------------------------------------------
    public DataItem getItem(int index)   
       { return itemArray[index]; }
 // -------------------------------------------------------------
    public boolean isFull()
       { return (numItems==ORDER-1) ? true : false; }
 // -------------------------------------------------------------
    public int findItem(double key)                //Mencari dengan Binary Search
       {                                    
       for(int j=0; j<ORDER-1; j++)         
          {                                 
          if(itemArray[j] == null)         
             break;
          else if(itemArray[j].dData == key)
             return j;
          }
       return -1;
       }  
 // -------------------------------------------------------------
    public int insertItem(DataItem newItem)      //Menambahkan Nilai baru pada Key
       {
       numItems++;                          
       double newKey = newItem.dData;       
       for(int j=ORDER-2; j>=0; j--)             //Untuk Mencheck Key, Jika Full di
          {                                      //Turunkan ke Kiri.
          if(itemArray[j] == null)          
             continue;                      
          else                              
             {                              
             double itsKey = itemArray[j].dData;
             if(newKey < itsKey)                 // Jika Nilainya lebih besar maka
                itemArray[j+1] = itemArray[j];   // Diturunkan ke kanan.
             else
                {
                itemArray[j+1] = newItem;   
                return j+1;                 
                }                           
             }  
          }                  
       itemArray[0] = newItem;              
       return 0;
       }  
 // -------------------------------------------------------------
    public DataItem removeItem()       
       {
       DataItem temp = itemArray[numItems-1];  
       itemArray[numItems-1] = null;           
       numItems--;                             
       return temp;                            
       }
 // -------------------------------------------------------------
    public void displayNode()                    //Menampilkan Node dengan format "/3/4/8"
       {
       for(int j=0; j<numItems; j++)
          itemArray[j].displayItem();   
       System.out.println("/");         
       }
 
}  

