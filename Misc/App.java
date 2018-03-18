package guekho64.Test;

import java.security.GeneralSecurityException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.zip.ZipException;
import javax.security.auth.DestroyFailedException;
import com.fathzer.soft.javaluator.DoubleEvaluator;

public final class App {
  public static final int impossibleNumber = (641 - 1);
  public static final DoubleEvaluator doubleEvaluator = new DoubleEvaluator();
  public static final void main(final String... arguments) {
    double a = impossibleNumber;
    @SuppressWarnings("unused")
    double b = impossibleNumber;
    @SuppressWarnings("unused")
    double c = impossibleNumber;
    if (arguments.length != 3) {
      throw new RuntimeException();
    }
    for (int counter = 0; (counter < arguments.length); ++counter) {
      switch (counter) {
        case 0 : {
          if (!(arguments[counter].isEmpty())) {
            a = Double.valueOf(arguments[counter]);
          }
          break;
        }
        case 1 : {
          if (!(arguments[counter].isEmpty())) {
            b = Double.valueOf(arguments[counter]);
          }
          break;
        }
        case 2 : {
          if (!(arguments[counter].isEmpty())) {
            c = Double.valueOf(arguments[counter]);
          }
          break;
        }
        default : {
          throw new RuntimeException();
        }
      }
    }
    final DestroyFailedException destroyFailedException = new DestroyFailedException();
    final GeneralSecurityException generalSecurityException = new GeneralSecurityException();
    final ZipException zipException = new ZipException();
    @SuppressWarnings("serial")
    final List<Tuple<String, Double>> iList= new ArrayList<Tuple<String, Double>>() {{
      add(new Tuple<String, Double>("y", 100D));
      add(new Tuple<String, Double>("p", 75D));
      add(new Tuple<String, Double>("m", 50D));
      add(new Tuple<String, Double>("o", 25D));
      add(new Tuple<String, Double>("x", 0D));
    }};
    @SuppressWarnings("serial")
    final List<Tuple<String, Double>> iList2= new ArrayList<Tuple<String, Double>>() {{
      add(new Tuple<String, Double>("y", Double.valueOf(arguments[1])));
      add(new Tuple<String, Double>("p", 0D));
      add(new Tuple<String, Double>("m", 0D));
      add(new Tuple<String, Double>("o", 0D));
      add(new Tuple<String, Double>("x", Double.valueOf(arguments[2])));
    }};
    iList2.get(1).setValue(((((iList2.get(0).getValue() + iList2.get(iList2.size() - 1).getValue())/2) + iList2.get(0).getValue())/2));
    iList2.get(2).setValue(((iList2.get(0).getValue() + iList2.get(iList2.size() - 1).getValue())/2));
    iList2.get(3).setValue(((iList2.get(iList2.size() -1).getValue() + ((iList2.get(0).getValue() + iList2.get(iList2.size() - 1).getValue())/2))/2));
    final Tuple<String, Double> y = iList.get(0);
    final Tuple<String, Double> p = iList.get(1);
    final Tuple<String, Double> m = iList.get(2);
    final Tuple<String, Double> o = iList.get(3);
    final Tuple<String, Double> x = iList.get(4);
    /** Math **/
    final double objectivePercentage = a;
    boolean Y = false;
    boolean P = false;
    boolean M = false;
    boolean O = false;
    boolean X = false;
    boolean done = false;
    boolean firstAttempt = true;
    double percentage = impossibleNumber;
    int interestingIndex1 = impossibleNumber;
    int interestingIndex2 = impossibleNumber;
    double highestNumber = impossibleNumber;
    double lowestNumber = impossibleNumber;
    int preciseIndex = impossibleNumber;
    double finalPercentage = impossibleNumber;
    @SuppressWarnings("serial")
    List<Boolean> bList = new ArrayList<Boolean>() {{
      add(y.getValue() == objectivePercentage);
      add(p.getValue() == objectivePercentage);
      add(m.getValue() == objectivePercentage);
      add(o.getValue() == objectivePercentage);
      add(x.getValue() == objectivePercentage);
    }};
    @SuppressWarnings("serial")
    List<Tuple<String, Boolean>> bList2 = new ArrayList<Tuple<String, Boolean>>() {{
      add(new Tuple<String, Boolean>("y", (y.getValue() > objectivePercentage)));
      add(new Tuple<String, Boolean>("p", (p.getValue() > objectivePercentage)));
      add(new Tuple<String, Boolean>("m", (m.getValue() > objectivePercentage)));
      add(new Tuple<String, Boolean>("o", (o.getValue() > objectivePercentage)));
      add(new Tuple<String, Boolean>("x", (x.getValue() > objectivePercentage)));
    }};
    String highestStackWall = null;
    String lowestStackWall = null;
    String finalBuilder = "";
    StringBuilder stringBuilder = new StringBuilder();
    try {
      while (true) {
        if (firstAttempt) {
          for (int counter = 0; (counter < bList.size()); ++counter) {
            if (bList.get(counter)) {
              preciseIndex = counter;
              throw zipException;
            }
          }
          for (int counter = 0; (counter < bList2.size()); ++counter) {
            if ((bList2.get(counter).getValue() == true) && (bList2.get(counter + 1).getValue() == false)) {
              interestingIndex1 = counter;
              interestingIndex2 = (counter + 1);
              highestNumber = iList.get(interestingIndex1).getValue();
              lowestNumber = iList.get(interestingIndex2).getValue();
              BetweenTwo(Sum(stringBuilder, highestNumber, lowestNumber));
              percentage = Solve(stringBuilder);
              if (Verify(percentage, objectivePercentage)) {
                finalPercentage = percentage;
                throw destroyFailedException;
              }
            }
          }
          firstAttempt = false;
        }
        else {
          if ((highestStackWall == null) && (lowestStackWall == null)) {
            if (percentage > objectivePercentage) {
              highestStackWall = ToString(stringBuilder);
              BetweenTwo(Sum(stringBuilder, lowestNumber));
            }
            else if (percentage < objectivePercentage){
              lowestStackWall = ToString(stringBuilder);
              BetweenTwo(Sum(stringBuilder, highestNumber));
            }
          }
          else {
            if (highestStackWall != null) {
              if (percentage > objectivePercentage) {
                highestStackWall = ToString(stringBuilder);
                if (lowestStackWall == null) {
                  BetweenTwo(Sum(stringBuilder, lowestNumber));
                }
                else {
                  BetweenTwo(Sum(stringBuilder, lowestStackWall));
                }
              }
              else if (percentage < objectivePercentage){
                lowestStackWall = ToString(stringBuilder);
                BetweenTwo(Sum(stringBuilder, highestStackWall));
              }
            }
            else if (lowestStackWall != null) {
              if (percentage < objectivePercentage) {
                lowestStackWall = ToString(stringBuilder);
                BetweenTwo(Sum(stringBuilder, highestNumber));
              }
              else if (percentage > objectivePercentage) {
                highestStackWall = ToString(stringBuilder);
                BetweenTwo(Sum(stringBuilder, lowestStackWall));
              }
            }
          }
          percentage = Solve(stringBuilder);
          if (Verify(percentage, objectivePercentage)) {
            finalPercentage = percentage;
            throw generalSecurityException;
          }
        }
      }
    }
    catch (ZipException fictionalProblem) {
      if (preciseIndex != impossibleNumber) {
        System.out.println(iList2.get(preciseIndex).getValue());
        done = false;
      }
    }
    catch (DestroyFailedException fictionalProblem) {
      if (finalPercentage != impossibleNumber) {
        //System.out.println(finalPercentage);
        done = true;
      }
    }
    catch (GeneralSecurityException fictionalProblem) {
      if (finalPercentage != impossibleNumber) {
        //System.out.println(finalPercentage);
        done = true;
      }
    }
    if (done) {
      switch (interestingIndex1) {
        case (0): {
          Y = true;
          break;
        }
        case (1): {
          P = true;
          break;
        }
        case (2): {
          M = true;
          break;
        }
        case (3): {
          O = true;
          break;
        }
        case (4): {
          X = true;
          break;
        }
        default: {
          throw new RuntimeException();
        }
      }
      switch (interestingIndex2) {
        case (0): {
          Y = true;
          break;
        }
        case (1): {
          P = true;
          break;
        }
        case (2): {
          M = true;
          break;
        }
        case (3): {
          O = true;
          break;
        }
        case (4): {
          X = true;
          break;
        }
        default: {
          throw new RuntimeException();
        }
      }
      finalBuilder = ToString(stringBuilder);
      if (Y) {
        finalBuilder = finalBuilder.replaceAll("100\\.0", String.valueOf(iList2.get(0).getValue()));
      }
      if (P) {
        finalBuilder = finalBuilder.replaceAll("75\\.0", String.valueOf(iList2.get(1).getValue()));
      }
      if (M) {
        finalBuilder = finalBuilder.replaceAll("50\\.0", String.valueOf(iList2.get(2).getValue()));
      }
      if (O) {
        finalBuilder = finalBuilder.replaceAll("25\\.0", String.valueOf(iList2.get(3).getValue()));
      }
      if (X) {
        finalBuilder = finalBuilder.replaceAll("0\\.0", String.valueOf(iList2.get(3).getValue()));
      }
      if (!Y && !P && !M && !O && !X) {
        throw new RuntimeException();
      }
      //System.out.println((stringBuilder));
      System.out.println(Solve(finalBuilder));
    }
    else {
      throw new RuntimeException();
    }
  }
  public static final String ToString (StringBuilder stringBuilder) {
    return stringBuilder.toString();
  }
  public static final double Solve (StringBuilder stringBuilder) {
    return Solve(ToString(stringBuilder));
  }
  public static final double Solve (String expression) {
    return doubleEvaluator.evaluate(expression);
  }
  public static final boolean Verify (final double objectiveNumber, final double tolerance, final double actualNumber) {
    if ( !(actualNumber > (objectiveNumber + tolerance)) && !(actualNumber < (objectiveNumber - tolerance)) ) {
      return true;
    }
    else {
      return false;
    }
  }
  public static final boolean Verify (final double objectiveNumber, final double actualNumber) {
    return Verify(objectiveNumber, 0.000001D, actualNumber);
  }
  public static final StringBuilder BetweenTwo (final StringBuilder stringBuilder) {
    return Wrap(stringBuilder.append("/2"));
  }
  public static final StringBuilder Sum (final StringBuilder stringBuilder, final double aNumber) {
    return Wrap(stringBuilder.append("+").append(aNumber));
  }
  public static final StringBuilder Sum (final StringBuilder stringBuilder, final String string) {
    return Wrap(stringBuilder.append("+").append(string));
  }
  public static final StringBuilder Sum (final StringBuilder stringBuilder1, final StringBuilder stringBuilder2) {
    return Wrap(stringBuilder1.append("+").append(stringBuilder2));
  }
  public static final StringBuilder Sum (final StringBuilder stringBuilder, final double aNumber, final double anotherNumber) {
    return Wrap(stringBuilder.append(aNumber).append("+").append(anotherNumber));
  }
  public static final StringBuilder Wrap (final StringBuilder stringBuilder) {
    return stringBuilder.insert(0, "(").append(")");
  }
  public static final double Fixer (final double number) {
    return (number + 640);
  }
  @SuppressWarnings("serial")
  public static final class Tuple <K, V> extends SimpleEntry<K, V> {
    public Tuple(final Entry<? extends K, ? extends V> entry) {
      super(entry);
    }
    public Tuple(final K key, final V value) {
      super(key, value);
    }
  }
}
