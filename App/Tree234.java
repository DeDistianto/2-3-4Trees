package App;
public class Tree234 {                           // Kelas Node, kumpulan Fungsi pada Node
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
