/**
 * Auto Generated Java Class.
 */
public class Snippet {
  String function;
  String[] args;
  boolean shouldSplit;
  public Snippet(String a, String[] b){
    function = a;
    args = b;
  }
  public String[] argParse(){
   String[] argString = this.args;
   shouldSplit = !argString[0].contains("(");
   String[] splitArgs = argString;
   if (shouldSplit){
     splitArgs = argString[0].split(" ");
     System.out.println("---------");
     System.out.println(splitArgs.length);
    
     for (String item : splitArgs){
       System.out.println(item);
     }
   }
    for (String str : splitArgs){
      if (str.contains("...") && shouldSplit){
        str = "";
      }
        
    }
    return splitArgs;
  }
  
}
