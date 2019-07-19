
package sicxeop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class PassOne {
   String filepath,length,progname;
   int startadd,lastadd;
   String [] splitter = new String[5];
  public static ArrayList<String []> fullStack = new ArrayList<>();
 public static  ArrayList<String []> symboltab= new ArrayList<>();
 
    public PassOne(String path) throws FileNotFoundException, IOException
    {
        filepath=path;
        start();
        PassTwo passTwo=new PassTwo(fullStack, symboltab);
      
    }
    public void start() throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        while((br.ready())){
           String line=br.readLine().trim().toUpperCase();
       splitting(line);
       addressCounter();
       symbTab();
       fullStack.add(new String[] {splitter[0],splitter[1],splitter[2],splitter[3],splitter[4]});
                print();
       
        }
       
    }
    public void splitting(String line)
    {
        int i;
        for( i = 1; line.contains(" ") ; i++)
        {
            
                splitter[i]=line.substring(0,   line.indexOf(' '));
                line=line.substring(line.indexOf(' ')).trim();
               }
     if(i==1)
     {
         splitter[1]="  ";
         splitter[3]="  ";
         splitter[2]=line;
         splitter[4]="  ";
     }
     else if(i==2)
     {
         splitter[2] = splitter[1];
        
         splitter[1]="  ";
         splitter[3]=line;
         splitter[4]="  ";
         
     }
     else
     {
         splitter[3]=line;
         splitter[4]="  ";
     }
     
    }
    public void addressCounter()
    {
        if(splitter[2].equals("START"))
        {
            splitter[0] = splitter[3];
            startadd=Integer.parseInt(splitter[0],16);
            lastadd=startadd;
        }
        else if(splitter[2].equals("BASE"))
        {
            splitter[0]=Integer.toHexString(lastadd);
        }
        else if(splitter[2].equals("WORD"))
        {
            splitter[0]=Integer.toHexString(lastadd);
            lastadd+=splitter[3].split(",").length*3;
        }
        else if(splitter[2].equals("RESW"))
        {
            splitter[0]=Integer.toHexString(lastadd);
            lastadd+=Integer.parseInt(splitter[3])*3;
            
        }
        else if(splitter[2].equals("RESB"))
        {
            splitter[0]=Integer.toHexString(lastadd);
            lastadd+=Integer.parseInt(splitter[3]);
            
        }
        else if(splitter[2].equals("BYTE"))
        {
            splitter[0]=Integer.toHexString(lastadd);
            if(splitter[3].contains("C'"))
            {
                //splitter[0]=Integer.toHexString(lastadd);
                
               lastadd+=splitter[3].length()-3;            }
            else
            {
                lastadd+=(splitter[3].length()-3)/2;
            }
            
                  
        }
        else if(splitter[2].equals("BASE"))
                    {
                    splitter[0]="  ";
                    }
        else if(splitter[2].equals("END"))
        {
            splitter[0]=Integer.toHexString(lastadd);
        }
        else if(splitter[2].contains("+"))
        {
            splitter[0]=Integer.toHexString(lastadd);
            lastadd+=4;
            
        }
    else
        {
            splitter[0]=Integer.toHexString(lastadd);
           // System.out.println(FORMATANDOPCODEANDREGISTERS.getFormatNumber(splitter[2]));
            lastadd+=FORMATANDOPCODEANDREGISTERS.getFormat(splitter[2]);
              
        }
        
    
}
    public void symbTab()
    {
        //String[] x;
        if(!splitter[1].equals("  ") && !splitter[2].equals("START")&&!splitter[2].equals("END"))
          //symboltab.add(splitter[0],splitter[1]);
            //x={splitter[0],splitter[1]};
            symboltab.add(new String[] {splitter[0],splitter[1]});
    }
    public void print() throws FileNotFoundException, UnsupportedEncodingException
    {PrintWriter writer = new PrintWriter("PASSONE.txt", "UTF-8");

/*for(String [] x : fullStack)
        {
      System.out.println(x[0]+"^^"+x[1]+"^^"+x[2]+"^^"+x[3]);
        }*/
//writer.close();
       writer.println("Address Table : ");
        for(String [] x : fullStack)
        {
        writer.println(x[0]);
        }
        writer.println("SYMBOL TABLE : ");
        for(String [] x:symboltab)
        {
            writer.println(x[0]+" "+x[1]);
        }
        writer.close();
    }
    public static ArrayList<String []> getFullStack()
    {
        return fullStack;
    }
    public static ArrayList<String []> getSymbTab()
    {
        return symboltab;
    }
    
    public static String getAddress(String line) {
        if (line.startsWith("#") || line.startsWith("@"))
            line = line.substring(1);

        if (line.endsWith(",X"))
            line = line.substring(0, line.indexOf(','));

        if (PassTwo.isNumeric(line))
            return line;
        else {
            
           // while(!symboltab.get(i++)[1].equals(line));
                /*for(i=0;i<symboltab.size()&&!symboltab.get(i)[1].equals(line);i++)
         {
             
         }
            return symboltab.get(i)[0];*/
       
         for(String [] x:symboltab)
         {
             if(x[1]==line)
             {
                 return x[0];
             }
                
         }
         return "0";
        }
    }
}
