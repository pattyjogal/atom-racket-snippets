import java.io.*;
import java.util.ArrayList;
public class CSONParse {

  public static void generateCSON(ArrayList<Snippet> data){
    String fileName = "racketSnippets.cson";
    
    try {
     FileWriter fw = new FileWriter(fileName);
     
     BufferedWriter bw = new BufferedWriter(fw);
     
     bw.write("'.source.racket':");
     bw.newLine();
      for (Snippet snip : data){
       bw.write("\t'" + snip.function + " function':");
       bw.newLine();
       bw.write("\t\t'prefix': '" + snip.function + "'");
       bw.newLine();
       bw.write("\t\t'body': \"(" + snip.function + ")\"");
       bw.newLine();
      }
     
     
     bw.close();
     
    }catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    
  }
  
  public static void readFile(String filename)throws FileNotFoundException, IOException{
    boolean nextLine = false;
    ArrayList<String> functions = new ArrayList<String>();
    ArrayList<String[]> splitFunctions = new ArrayList<String[]>();
    ArrayList<Snippet> snippets = new ArrayList<Snippet>();
     try (BufferedReader br = new BufferedReader(new FileReader(filename))){
       for (String line; (line = br.readLine()) != null; ) {
         
          if (line.contains("function")){
            nextLine = true;
            
          }else if (nextLine) {
            nextLine = false;
            line = line.replace("Â", "");
            functions.add(line);
          }else{
            nextLine = false;
          }
       }
       
       for(String item : functions){
         String[] splitItem;
         splitItem = item.split(":", 2);
         
         
         splitFunctions.add(splitItem);
       }
       
       for(String[] item : splitFunctions){
         Snippet snip = new Snippet("","");
         snip.function = item[0].replace(" ", ""); 
         String tempArg = item[1];
         tempArg = tempArg.substring(2);
         snip.args = tempArg.split("-> [^\\%]",2)[0];
         snippets.add(snip);
       }
       for(Snippet item : snippets){
         System.out.println(item.function + " which has the syntax " + item.args);
         //System.out.println(item.args);
       }
       generateCSON(snippets);
     }
  }

  public static void main(String[] args) throws FileNotFoundException, IOException{
    String path = "C:\\Program Files\\Racket\\doc\\deinprogramm\\blueboxes.txt";
    path = path.replace("\\", "/");
    readFile(path);
    File file =  new File(path);
    //System.out.println(file.exists());
  }
}
