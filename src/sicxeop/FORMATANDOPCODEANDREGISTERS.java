
package sicxeop;

import static sicxeop.PassOne.symboltab;


public class FORMATANDOPCODEANDREGISTERS {
  public static String[][]  OPC = new String[59][3];
public static String[][] REGISTERS = new String[9][2];


    
    public static void start()
    {
        OPC[0] = new String[] {"MULF", "3", "60"};
        OPC[1] = new String[] {"OR", "3", "44"};
        OPC[2] = new String[] {"RD", "3", "D8"};
        OPC[3] = new String[] {"RSUB", "3", "4C"};
        OPC[4] = new String[] {"SSK", "3", "EC"};
        OPC[5] = new String[] {"STA", "3", "0C"};
        OPC[6] = new String[] {"STB", "3", "78"};
        OPC[7] = new String[] {"STCH", "3", "54"};
        OPC[8] = new String[] {"STF", "3", "80"};
        OPC[9] = new String[] {"STI", "3", "D4"};
        OPC[10] = new String[] {"STL", "3", "14"};
        OPC[11] = new String[] {"STS", "3", "7C"};
        OPC[12] = new String[] {"STSW", "3", "E8"};
        OPC[13] = new String[] {"STT", "3", "84"};
        OPC[14] = new String[] {"STX", "3", "10"};
        OPC[15] = new String[] {"SUB", "3", "1C"};
        OPC[16] = new String[] {"SUBF", "3", "5C"};
        OPC[17] = new String[] {"TD", "3", "E0"};
        OPC[18] = new String[] {"TIX", "3", "2C"};
        OPC[19] = new String[] {"WD", "3", "DC"};
        OPC[20] = new String[] {"FIX", "1", "C4"};
        OPC[21] = new String[] {"FLOAT", "1", "C0"};
        OPC[22] = new String[] {"HIO", "1", "F4"};
        OPC[23] = new String[] {"NORM", "1", "C8"};
        OPC[24] = new String[] {"SIO", "1", "F0"};
        OPC[25] = new String[] {"TIO", "1", "F8"};
        OPC[26] = new String[] {"ADDR", "2", "90"};
        OPC[27] = new String[] {"CLEAR", "2", "B4"};
        OPC[28] = new String[] {"COMPR", "2", "A0"};
        OPC[29] = new String[] {"DIVR", "2", "9C"};
        OPC[30] = new String[] {"MULR", "2", "98"};
        OPC[31] = new String[] {"RMO", "2", "AC"};
        OPC[32] = new String[] {"SHIFTL", "2", "A4"};
        OPC[33] = new String[] {"SHIFTR", "2", "A8"};
        OPC[34] = new String[] {"SUBR", "2", "94"};
        OPC[35] = new String[] {"SVC", "2", "B0"};
        OPC[36] = new String[] {"TIXR", "2", "B8"};
        OPC[37] = new String[] {"ADD", "3", "18"};
        OPC[38] = new String[] {"ADDF", "3", "58"};
        OPC[39] = new String[] {"AND", "3", "40"};
        OPC[40] = new String[] {"COMP", "3", "28"};
        OPC[41] = new String[] {"COMPF", "3", "88"};
        OPC[42] = new String[] {"DIV", "3", "24"};
        OPC[43] = new String[] {"DIVF", "3", "64"};
        OPC[44] = new String[] {"J", "3", "3C"};
        OPC[45] = new String[] {"JEQ", "3", "30"};
        OPC[46] = new String[] {"JGT", "3", "34"};
        OPC[47] = new String[] {"JLT", "3", "38"};
        OPC[48] = new String[] {"JSUB", "3", "48"};
        OPC[49] = new String[] {"LDA", "3", "00"};
        OPC[50] = new String[] {"LDB", "3", "68"};
        OPC[51] = new String[] {"LDCH", "3", "50"};
        OPC[52] = new String[] {"LDF", "3", "70"};
        OPC[53] = new String[] {"LDL", "3", "08"};
        OPC[54] = new String[] {"LDS", "3", "6C"};
        OPC[55] = new String[] {"LDT", "3", "74"};
        OPC[56] = new String[] {"LDX", "3", "04"};
        OPC[57] = new String[] {"LPS", "3", "D0"};
        OPC[58] = new String[] {"MUL", "3", "20"};
        REGISTERS[0] = new String[] {"A","0"};
        REGISTERS[1] = new String[] {"X","1"};
        REGISTERS[2] = new String[] {"L","2"};
        REGISTERS[3] = new String[] {"B","3"};
        REGISTERS[4] = new String[] {"S","4"};
        REGISTERS[5] = new String[] {"T","5"};
        REGISTERS[6] = new String[] {"F","6"};
        REGISTERS[7] = new String[] {"PC","8"};
        REGISTERS[8] = new String[] {"SW","9"};
        
    }
    public static String getOpcode(String line) {
        if (line.startsWith("+"))
            line = line.substring(1);

        
              int i=0;
       for(i=0;i<OPC.length&&!line.equals(OPC[i][0]);i++);
         
        return OPC[i][2];}
  
   public static int getFormat(String line)
   {
     if(line.startsWith("+"))
           line=line.substring(1);
       int i=0;
       for(i=0;i<OPC.length&&!line.equals(OPC[i][0]);i++);
        
         return Integer.parseInt(OPC[i][1]);
    
               }
   
   public static  String getRegisterNumber(String line) {
        int i=0;
       for(i=0;i<OPC.length&&!line.equals(REGISTERS[i][0]);i++);
         
        return Integer.toHexString( Integer.parseInt(REGISTERS[i][1] ) );
    }
   

      
    
              
    
    
    
}
