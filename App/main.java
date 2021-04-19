package App;

// 2-3-4 Trees
// 2-3-4 Trees Simulation
import java.io.*;                                        // untuk I/O
import java.lang.Integer;                                // untuk parseInt()
// -------------------------------------------------------------
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
