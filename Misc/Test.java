package guekho64.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap.SimpleImmutableEntry;

public final class Main {

  public static final void main(final String... args) {

    final int objectivePercentage = Solver(args[0]);
    
    if (args[0].isEmpty()) {
      System.out.println("Error -1");
      System.exit(-1);
    }
    else if (objectivePercentage == -64) {
      System.out.println("Error -2");
      System.exit(-2);
    }
    else if ( (objectivePercentage < 0) || (objectivePercentage > 100) ) {
      System.out.println("Error -3");
      System.exit(-3);
    }
    
    
    final SimpleImmutableEntry<String, Integer> x = new SimpleImmutableEntry<String, Integer>("x", 0);
    final SimpleImmutableEntry<String, Integer> y = new SimpleImmutableEntry<String, Integer>("y", 100);
    final SimpleImmutableEntry<String, Integer> m = new SimpleImmutableEntry<String, Integer>("m", ((x.getValue() + y.getValue())/2));
    final SimpleImmutableEntry<String, Integer> p = new SimpleImmutableEntry<String, Integer>("p", ((m.getValue() + y.getValue())/2));
    final SimpleImmutableEntry<String, Integer> o = new SimpleImmutableEntry<String, Integer>("o", ((x.getValue() + m.getValue())/2));
    
    
    
    System.out.println(Exec("uname -r"));
    

  }
  
  public static final int Solver (final String numberInString) {
    try {
      return (int) Integer.valueOf(numberInString);
    }
    catch (NumberFormatException problem) {
      return -64;
    }
  }
  
  public static final String Exec (final String command) {

   final  StringBuffer output = new StringBuffer();

   final Process process = Solver2(command);
   
   if (process == null) {
     throw new RuntimeException("Ded");
   }
      
   Solver3(process);
   
   final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
   
   String line = "";
   
   while ((line = Solver4(reader)) != null) {
     output.append(line + "\n");
   }
   
   return output.toString();

  }
  
  public static final Process Solver2 (final String command) {
    try {
      return Runtime.getRuntime().exec(command);
    }
    catch (IOException problem) {
      return null;
    }
  }
  
  public static final void Solver3 (final Process process) {
    try {
      process.waitFor();
    }
    catch (InterruptedException problem) {
      throw new RuntimeException("Ded2");
    }
  }
  
  public static final String Solver4 (final BufferedReader bufferedReader) {
    try {
      return bufferedReader.readLine();
    }
    catch (IOException problem) {
      throw new RuntimeException("Ded3");
    }
  }

}
