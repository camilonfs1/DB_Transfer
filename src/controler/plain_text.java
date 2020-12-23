package controler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class plain_text {
	public static ArrayList<String> text(String ruta) {	
		String strLine = "";
   	    StringBuilder sb = new StringBuilder();  
   	    ArrayList<String> objects = new ArrayList();
     try
      {
    	 
         /*String filename= "../myfile.txt";
         FileWriter fw = new FileWriter(filename,false); 
         //appends the string to the file
         fw.write("Python Exercises\n");
         fw.close();*/
         BufferedReader br = new BufferedReader(new FileReader(ruta));
         //read the file content
         while (strLine != null)
         {
            sb.append(strLine);
            sb.append(System.lineSeparator());
            strLine = br.readLine();
            //System.out.println(strLine);
            objects.add(strLine);
        }
         br.close();                          
       }
       catch(IOException ioe)
       {
        System.err.println("IOException: " + ioe.getMessage());
       }
     return objects;
    }
	
}
