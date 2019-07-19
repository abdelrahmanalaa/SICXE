
package sicxeop;

import javax.swing.JFileChooser;
import java.io.File;   
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.filechooser.FileFilter;


public class SICXEOP {

   
    public static void main(String[] args) throws FileNotFoundException, IOException {
         

         
      JFileChooser chooser = new JFileChooser();
      String path="";               
      chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      chooser.setAcceptAllFileFilterUsed(false);
      chooser.addChoosableFileFilter(new FileFilter() {
          @Override
          public boolean accept(File f) {
              return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
          }

          @Override
          public String getDescription() {
              return "Text Files (*.txt)";
          }
      });
                  
    switch (chooser.showOpenDialog(chooser)) {
                           case JFileChooser.APPROVE_OPTION:
                                 path = chooser.getSelectedFile().getAbsolutePath();
                                 break;
     }
    FORMATANDOPCODEANDREGISTERS.start();
    FORMATANDOPCODEANDREGISTERS format=new FORMATANDOPCODEANDREGISTERS();
    PassOne passOne= new PassOne(path);
 
    }
    
}
