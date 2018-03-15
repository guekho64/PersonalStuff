package guekho64.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipException;

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
    
    
    final String baseCommand = ("python -c" + " ") + (
        (( "x=(#*1.0);".replaceAll("\\#", String.valueOf(x.getValue())) +
        "y=($*1.0);".replaceAll("\\$", String.valueOf(y.getValue()))) +
        ( "m=(%*1.0);".replaceAll("\\%", String.valueOf(m.getValue())) +
        "p=(&*1.0);".replaceAll("\\&", String.valueOf(p.getValue())))) +
        "o=(?*1.0);".replaceAll("\\?", String.valueOf(o.getValue())));
    
    class PythonCommandObject {
      final String command;
      PythonCommandObject (final String command) {
        this.command = Main.PythonCommand(baseCommand, command);
      }
      String getCommand () {
        return command;
      }
      String executeCommand () {
        return Exec(command);
      }
    }
    
    /** Math **/
    
    boolean firstAttempt = true;
    
    float percentage;
    
    int interestingIndex1;
    int interestingIndex2;
    
    int preciseIndex = -1;
    
    Float finalPercentage = null;
    
    @SuppressWarnings("serial")
    List<Boolean> bList = new ArrayList<Boolean>() {{
      add(y.getValue() > objectivePercentage);
      add(p.getValue() > objectivePercentage);
      add(m.getValue() > objectivePercentage);
      add(o.getValue() > objectivePercentage);
      add(x.getValue() > objectivePercentage);
    }};
    
    @SuppressWarnings("serial")
    List<Boolean> bList2 = new ArrayList<Boolean>() {{
      add(y.getValue() == objectivePercentage);
      add(p.getValue() == objectivePercentage);
      add(m.getValue() == objectivePercentage);
      add(o.getValue() == objectivePercentage);
      add(x.getValue() == objectivePercentage);
    }};
    
    String u;
    String v;
    
    try {

      while (true) {

        if (firstAttempt) {

          for (int counter = 0; counter < bList2.size(); ++counter) {
            if (bList2.get(counter)) {
              preciseIndex = counter;
              throw new ZipException();
            }
          }

          for (int counter = 0; counter < bList.size(); ++counter) {
            if ((bList.get(counter) == false) && (bList.get(counter++) == true)) {
              interestingIndex1 = counter;
              interestingIndex2 = counter++;
            }
            else {
              throw new RuntimeException("What just happened?");
            }
          }
          
          if 

          firstAttempt = false;

        }
        else {

        }

      }
    }
    catch (ZipException fictionalProblem) {
      assert true;
    }
    
    if (preciseIndex != -1) {
      switch (preciseIndex) {
        case (0): {
          System.out.println(y.getValue());
          break;
        }
        case (1): {
          System.out.println(p.getValue());
          break;
        }
        case (2): {
          System.out.println(m.getValue());
          break;
        }
        case (3): {
          System.out.println(o.getValue());
          break;
        }
        case (4): {
          System.out.println(x.getValue());
          break;
        }
        default: {
          throw new RuntimeException("Not gud!");
        }
      }
    }
    
//    if (finalPercentage != null) {
//      System.out.println(finalPercentage.floatValue());
//    }
//    else {
//      throw new RuntimeException("Duh!");
//    }
    

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

  public static final String PythonCommand (final String pythonCommand, final String python) {
    return (pythonCommand+ (python + ";"));
  }
  
}
