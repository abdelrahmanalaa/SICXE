
package sicxeop;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;




public class PassTwo {
   ArrayList<String []> fullStack = new ArrayList<>();
ArrayList<String []> symbolTable=new ArrayList<>();
ArrayList<String > HTE = new ArrayList<>();
ArrayList<String  > objectcode = new ArrayList<>();
String pc,base,ta,length;
int opcode=0,flags=0,disp=0;
    public PassTwo(ArrayList<String []> fullStack,ArrayList<String []> symbolTable) throws FileNotFoundException, UnsupportedEncodingException {
        this.fullStack=fullStack;
        this.symbolTable=symbolTable;
        objCodeGen();
        int first=Integer.parseInt(fullStack.get(fullStack.size()-1)[0])-Integer.parseInt(fullStack.get(0)[0]);
        length=Integer.toHexString(first);
        print();
        }
    public static boolean isNumeric(String s) {
    try { 
        Integer.parseInt(s); 
    } catch(Exception e) { 
        return false; 
    
    }
   
    return true;
}
    public void objCodeGen() throws FileNotFoundException, UnsupportedEncodingException
    {
       for(int i=0;i<fullStack.size();i++)
       {
           
          fullStack.get(i)[2]= fullStack.get(i)[2].toUpperCase();
           opcode=0;flags=0;disp=0;
           /*if(i<fullStack.size()-2)
               if(fullStack.get(i)[0].equals(fullStack.get(i--)[0]))
               {
                   pc=fullStack.get(i+1)[0];
               }
           else
               {
                   pc=
               }*/
                 if(i<fullStack.size()-2)
                 {
                     if(fullStack.get(i+1)[0].equals(fullStack.get(i)[0]))
                         pc=fullStack.get(i+2)[0];
                     else
                         pc=fullStack.get(i+1)[0];
                 }
                 
                 if(!fullStack.get(i)[2].equals("START")   && !fullStack.get(i)[2].equals("RESB") && !fullStack.get(i)[2].equals("RESW")&&!fullStack.get(i)[2].equals("BASE")&&!fullStack.get(i)[2].equals("END"))
                 {
                    
                     if(fullStack.get(i)[2].equals("BYTE"))
                     {
                         if(fullStack.get(i)[3].contains("X'"))
                         {
                             fullStack.get(i)[4]=fullStack.get(i)[3].substring(1,fullStack.get(i)[3].lastIndexOf('\''));
                         }
                         else
                         {
                             fullStack.get(i)[4]=fullStack.get(i)[3].substring(2, fullStack.get(i)[3].lastIndexOf('\''));
                         }
                         
                     }
                     else if(fullStack.get(i)[2].equals("WORD"))
                     {
                         if(fullStack.get(i)[3].contains(","))
                         {
                             String [] array=fullStack.get(i)[3].split(",");
                             String x="";
                             for(int z=0;z<array.length;z++)
                             {
                                 x+=("000000" + Integer.toHexString(Integer.parseInt(array[z]))).substring(Integer.toHexString(Integer.parseInt(array[z])).length());
                                 if(z+1<x.length())
                                     x+=",";

                             }
                             fullStack.get(i)[4]=x;
                         }
                         else
                         {
                             
                             int x=Integer.parseInt(fullStack.get(i)[3]);
                             String word=Integer.toHexString(x);
                             fullStack.get(i)[4]=("000000" + word).substring(word.length());
                             
                            
                         }
                     }
                     else if(fullStack.get(i)[2].equals("RSUB"))
                     {
                         fullStack.get(i)[4]="00004C";
                         
                         
                     }
                         else
                     {
                      
                         if(FORMATANDOPCODEANDREGISTERS.getFormat(fullStack.get(i)[2])==1)
                         {
                             
                             fullStack.get(i)[4]=FORMATANDOPCODEANDREGISTERS.getOpcode(fullStack.get(i)[2]); }
                             else if(FORMATANDOPCODEANDREGISTERS.getFormat(fullStack.get(i)[2])==2)
                                     {
                                       
                                     String [] array=fullStack.get(i)[3].split(",");
                                     String x="";
                                     for(int loop=0;loop<array.length;loop++)
                                     {
                                         x+=FORMATANDOPCODEANDREGISTERS.getRegisterNumber(array[loop]);
                                     }
                                    fullStack.get(i)[4]=x;
                                     }
                              
                             else if(fullStack.get(i)[3].startsWith("@"))
                             
                             
                              opcode+=Integer.parseInt(FORMATANDOPCODEANDREGISTERS.getOpcode(fullStack.get(i)[2]), 16)+2;
                             else if(fullStack.get(i)[3].startsWith("#"))
                                 opcode+=Integer.parseInt(FORMATANDOPCODEANDREGISTERS.getOpcode(fullStack.get(i)[2]), 16)+1;
                             
                         else
                                 opcode+=Integer.parseInt(FORMATANDOPCODEANDREGISTERS.getOpcode(fullStack.get(i)[2]), 16)+3;
                         
                         ta=PassOne.getAddress(fullStack.get(i)[3]);
                      
                        if(fullStack.get(i)[2].startsWith("+"))
                         {
                             flags+=1;
                             if(fullStack.get(i)[3].startsWith("#"))
                             {
                                 if(isNumeric(fullStack.get(i)[3].substring(1)))
                                 {
                                     //disp=Integer.parseInt(PassOne.getAddress(fullStack.get(i)[3]));   
                                     disp=Integer.parseInt(ta);
                                 }
                                 
                             }
                             else
                             {
                                 disp=Integer.parseInt(ta,16);
                             }
                             
                             String x=("00" + Integer.toHexString(opcode)).substring(Integer.toHexString(opcode).length());
                             String z=("000" + Integer.toHexString(disp)).substring(Integer.toHexString(disp).length());
                             fullStack.get(i)[4]=x+Integer.toHexString(flags)+z;
                             
                         }
                         else
                         {
                             int displacement= Integer.parseInt(ta, 16)-Integer.parseInt(pc,16);
                             if(fullStack.get(i)[3].startsWith("#") && isNumeric(fullStack.get(i)[3].substring(1)))
                             {
                                 disp=Integer.parseInt(ta);
                             }
                              else if (-2048 <= displacement && displacement <= 2047) {
                                  disp = displacement;
                                  flags += 2;
                
            }
                             else {
                                  
                                     flags += 4;
                                    displacement = Integer.parseInt(ta, 16) - Integer.parseInt(pc, 16);
                                    disp = displacement;
            
                              }
                             String x=("00" + Integer.toHexString(opcode)).substring(Integer.toHexString(opcode).length());
            String z=("000" + Integer.toHexString(disp)).substring(Integer.toHexString(disp).length());
            fullStack.get(i)[4]=x+Integer.toHexString(flags)+z;

                                
                                
                             
                         }
                         
                             
                           
                         
                         
                     }
                     
       }
                 else 
                     fullStack.get(i)[4]="No Object Code";
                 
 
    }
    
       
        
    
    }  
    public void print() throws FileNotFoundException, UnsupportedEncodingException
    {
        PrintWriter writer = new PrintWriter("PASSTWO.txt", "UTF-8");
        writer.println("Object Code : ");
        for(String [] x : fullStack)
        {
        writer.println(x[4]);
        }
        
        writer.close();
        genHTE();
        
        for(String x: HTE)
        {
            System.out.println(x);
        }
    }
    public int getObjLength ()
    {
        int i=0;
        for(int z=0;z<fullStack.size();z++)
        {
            if(!fullStack.get(z)[4].equals("No Object Code"))
            {
                i++;
                objectcode.add(fullStack.get(z)[4]);
            }
            }
        
        return i;
    }
    public void genHTE()
    {
        int i;
        int size=getObjLength();
    
        HTE.add("H^"+fullStack.get(0)[1]+"^"+fullStack.get(0)[0]+"^"+length);
        if(size<=10)
        {
            String x="T^"+fullStack.get(0)[0]+"^"+Integer.toHexString((size*6)/2)+"^";
            for(int n=0;n<size;n++)
                x+=objectcode.get(n);
            HTE.add(x);
            HTE.add("E^"+fullStack.get(0)[0]);
            return;
        }
               else
        {
            int c=0;String x="";int loop=0;
             int result=0;
             x="T^"+fullStack.get(loop)[0]+"^"+Integer.toHexString((result*6)/2)+"^";
            for( loop=0;loop<objectcode.size();loop++)
            {
                
               
                if((objectcode.size()-loop)>10)
                        result=10;
                    else
                        result=objectcode.size()-loop-1;
                if(c<10)
                {
                    
                     
                    c++;
                x+=objectcode.get(loop);
                }
                if(loop==objectcode.size()-1)
                    HTE.add(x);
                
                if(c==10)
                {
                    HTE.add(x);
                    c=0;
                    x="T^"+fullStack.get(loop)[0]+"^"+Integer.toHexString((result*6)/2)+"^";
                }
            }
        }
       
           
           HTE.add("E^"+fullStack.get(0)[0]);
        }
       
            
    }

