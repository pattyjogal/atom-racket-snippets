import java.io.*;
import java.util.ArrayList;
public class CSONParse {

  //public static void generateCSON(ArrayList data)
  
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
       System.out.println(functions);
       for(String item : functions){
         String[] splitItem;
         splitItem = item.split(":", 2);
         System.out.println(item);
         
         splitFunctions.add(splitItem);
       }
       System.out.println("LIST OF FUNCTIONS:");
       for(String[] item : splitFunctions){
         Snippet snip = new Snippet("","");
         snip.function = item[0].replace(" ", ""); 
         
       }
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
