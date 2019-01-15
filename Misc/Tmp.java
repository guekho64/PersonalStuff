//TODO:Infinite Recursiveness Bug
//TODO:Array Inside Collection toString() bug
//TODO:SpecialList HashCode & Equals Method Override
//TODO:https://www.codejava.net/java-core/collections/18-java-collections-and-generics-best-practices (6, 2)
//TODO:Create Hash Registry (Beware of choosing the right collection to subclass...)
//TODO: [https://www.javatpoint.com/collections-in-java, https://www.geeksforgeeks.org/set-in-java/]

package misc.testing;

import static java.lang.System.out;

public final class HashContainer {
  public static final void main (final String... strings) {
    out.println("Active!");
    
    final Local.Types.Classes.Hash hash = new Local.Types.Classes.Hash();
    
    final byte[] a = new byte[1];
    
    out.println();
    
  }
  public static final class Local {
    public static final class Types {
      public static final class AbstractClasses {
        public static abstract class Hash {
          private final String hashType="Test";
          protected abstract StringBuilder byteArray2String (final byte[] byteArray);
          public Hash () {
            out.println(this.hashType);
          }
          public final String getHashType () {
            return this.hashType;
          }
        }
      }
      public static final class Classes {
        public static final class Hash extends AbstractClasses.Hash {

          Hash () {
            out.println(this.getHashType());
          }

          @Override
          protected StringBuilder byteArray2String (byte[] byteArray) {
            // TODO Auto-generated method stub
            return null;
          }
          
        }
      }
    }
  }
}
