// 2-3-4 Trees
// 2-3-4 Trees Simulation
import java.io.*;                                        // untuk I/O
import java.lang.Integer;                                // untuk parseInt()
// -------------------------------------------------------------
class DataItem
   {
   public double dData;                                 // one data item
// -------------------------------------------------------------
   public DataItem(double dd)                           // constructor
      { dData = dd; }
// -------------------------------------------------------------
   public void displayItem()                            // menampilkan item, format "/27"
      { System.out.print("/"+dData); }

   }  
//==============================================================
//==============================================================
//==============================================================
class Node
   {
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
//==============================================================
//==============================================================
//==============================================================
class Tree234                                   // Kelas Node, kumpulan Fungsi pada Node
   {
   private Node root = new Node();            

   public int find(double key)                  // Fungsi Untuk Mencari Nilai
      {
      Node curNode = root;
      int childNumber;
      while(true)
         {
         if(( childNumber=curNode.findItem(key) ) != -1)
            return childNumber;               
         else if( curNode.isLeaf() )
            return -1;                        
         else                                
            curNode = getNextChild(curNode, key);
         }  
      }

// -------------------------------------------------------------  
   public void insert(double dValue)             //Fungsi Untuk Memasukan Nilai
      {
      Node curNode = root;
      DataItem tempItem = new DataItem(dValue);

      while(true)
         {
         if( curNode.isFull() )              
            {
            split(curNode);                   
            curNode = curNode.getParent();    
                                             
            curNode = getNextChild(curNode, dValue);
            } 

         else if( curNode.isLeaf() )          
            break;                            
         else
            curNode = getNextChild(curNode, dValue);
         } 

      curNode.insertItem(tempItem);      
      }  
// -------------------------------------------------------------
   public void split(Node thisNode)              //Fungsi melakukan splitting pada Node
      {
      DataItem itemB, itemC;
      Node parent, child2, child3;
      int itemIndex;

      itemC = thisNode.removeItem();    
      itemB = thisNode.removeItem();    
      child2 = thisNode.disconnectChild(2); 
      child3 = thisNode.disconnectChild(3); 

      Node newRight = new Node();       

      if(thisNode==root)                
         {
         root = new Node();                
         parent = root;                    
         root.connectChild(0, thisNode);   
         }
      else                              
         parent = thisNode.getParent();    
     
      itemIndex = parent.insertItem(itemB); 
      int n = parent.getNumItems();         

      for(int j=n-1; j>itemIndex; j--)          
         {                                      
         Node temp = parent.disconnectChild(j); 
         parent.connectChild(j+1, temp);        
         }
           
      parent.connectChild(itemIndex+1, newRight);
   
      newRight.insertItem(itemC);       
      newRight.connectChild(0, child2); 
      newRight.connectChild(1, child3); 
      }  

// -------------------------------------------------------------   
   public Node getNextChild(Node theNode, double theValue) //Mencari child yang cocok pada 
      {                                                    //Node dalam pencarian nilai
      int j;
      int numItems = theNode.getNumItems();
      for(j=0; j<numItems; j++)          
         {                               
         if( theValue < theNode.getItem(j).dData )
            return theNode.getChild(j);  
         }  
      return theNode.getChild(j);       
      }
// -------------------------------------------------------------
   public void displayTree()                                   //Fungsi Menampilkan Tree
      {
      recDisplayTree(root, 0, 0);
      }
// -------------------------------------------------------------
   private void recDisplayTree(Node thisNode, int level,
                                              int childNumber) //Fungsi Untuk Menampilkan hasil Tree
      {
      System.out.print("Tingkat = "+level+"; Child = "+childNumber+" ");
      thisNode.displayNode();               

      int numItems = thisNode.getNumItems();
      for(int j=0; j<numItems+1; j++)
         {
         Node nextNode = thisNode.getChild(j);
         if(nextNode != null)
            recDisplayTree(nextNode, level+1, j);
         else
            return;
         }
      }  
}  

//==============================================================
//==============================================================
//==============================================================

class Tree234App              //Main App Menjalankan Program
   {
   public static void main(String[] args) throws IOException
      {
      double value;
      Tree234 theTree = new Tree234();   //Memanggil Fungsi" pada Class "Tree234"

      // theTree.insert(3);
      // theTree.insert(1);
      // theTree.insert(5);
      // theTree.insert(4);
      // theTree.insert(2);
      // theTree.insert(9);
      // theTree.insert(10);

      while(true)                         //Selama True Program akan jalan Terus
         {
         putText("Pilihan : ");
         putText("\n");
         putText("A. Show\n");
         putText("B. Insert\n");
         putText("C. Find\n");
         putText("\n");
         putText("Masukan Pilihan : ");
         char choice = getChar();
         switch(choice)
            {
            case 'A':   //Menampilkan Tree
               putText("=============================\n");
               theTree.displayTree();     
               putText("=============================\n");
               break;
            case 'B':   //Memasukan Nilai yang diinginkan
               putText("=============================\n");
               putText("Masukan Nilai untuk Insert  :");
               value = getInt();
               theTree.insert(value);
               putText("=============================\n");
               break;
            case 'C':   //Mencari Nilai yang dalam Tree
               putText("=============================\n");
               putText("Masukan Nilai untuk dicari  :");
               value = getInt();
               int found = theTree.find(value);
               if(found != -1)
                  System.out.println("Ditemukan : "+value);
               else
                  System.out.println("Tidak Ditemukan : "+value);
               break;
            default:
               putText("=============================\n");
               putText("Invalid entry\n");
            }  
         }  
      }  
//--------------------------------------------------------------
   //Fungsi menampilkan text
   public static void putText(String s)   
      {
      System.out.print(s);
      System.out.flush();
      }
//--------------------------------------------------------------
   public static String getString() throws IOException
      {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String s = br.readLine();
      return s;
      }
//--------------------------------------------------------------
   public static char getChar() throws IOException
      {
      String s = getString();
      return s.charAt(0);
      }

//-------------------------------------------------------------
   public static int getInt() throws IOException
      {
      String s = getString();
      return Integer.parseInt(s);
      }
//-------------------------------------------------------------
   }  
