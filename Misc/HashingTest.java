package guekho64.misc.testing;

import static java.lang.System.out;

import guekho64.misc.testing.HashingTest.Local.Types.MyList;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**

@Status Finished & Stable (If necesarry, bugfixes will continue to be released)

@Description
<p>This class was made only for very specific purposes.</p>

@author guekho64
@version 0.64
@Category Library
@since 06/01/2019 1:00 AM
@LastUpdated 06/01/2019 1:00 AM
@Link <a href="http://www.guekho64.webs.com">guekho64</a>
@License GNU AGPL v3

**/

public final class HashingTest {
    public static final class Local {
        public static final class Fields {
            /** This is the constant value to be used whenever overriding hashCode() on a custom Object **/
            public static final int expectedOverridenHashCode = 0;
        }
        public static final class Methods {
            /** This Method consumes byteArrays and outputs hexStrings **/
            public static final StringBuilder convertByteArrayToHexString (final byte[] bytesArray) {
                final StringBuilder stringBuilder = new StringBuilder();
                for (int counter = 0; counter < bytesArray.length; counter++) {
                    stringBuilder.append(Integer.toString((bytesArray[counter] & 0xff) + 0x100, 16).substring(1));
                }
                return stringBuilder;
            }
            /** This is the method that actually builds the SHA-256 key **/
            public static final <Generic> StringBuilder hash256 (final Generic generic) throws Throwable {
                try {
                    final MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    if (generic.hashCode() != Fields.expectedOverridenHashCode) {
                        throw new RuntimeException("Maybe you didn't override the method to return \"misc.testing.Main.Local.Fields.expectedOverridenHashCode\"...");
                    }
                    final byte[] hashedBytes = digest.digest(generic.toString().getBytes("UTF-8"));
                    return convertByteArrayToHexString(hashedBytes);
                }
                catch (Throwable problem) {
                    throw problem; // Implement a fix for this as you like!
                }
            }
            /** Wrapper method to get a String containing a SHA-256 key **/
            public static final <Generic> String generateSHA256 (final Generic generic) throws Throwable {
                return hash256(generic).toString();
            }
        }
        public static final class Types {
            /** Just an ArrayList implementing List, but with a custom hashCode method **/
            @SuppressWarnings("serial")
            public static class MyList<T> extends ArrayList<T>  implements List<T> {
                public MyList () {
                    super();
                }
                public static final int hash () {
                    return Fields.expectedOverridenHashCode;
                }
                @Override
                public int hashCode () {
                    return hash();
                }
            }
        }
    }
    public static final void main (final String... arguments) throws Throwable {
        /** Just a custom object, with a custom hashCode method **/
        final Object customObject1 = new Object() {
            @Override
            public int hashCode () {
                return Local.Fields.expectedOverridenHashCode;
            }
        };
        /** Another custom object, with many additions, most of which are useless. Just for testing purposes **/
        @SuppressWarnings("unused")
        final Object customObject2 = new Object() {
            @Override
            public int hashCode () {
                return Local.Fields.expectedOverridenHashCode;
            };
            private final String placeholderString = "string";
            {
                final int placeholderInteger = 1;
                final Object placeholderObject = new Object();
            }
        };
        /** A MyList Object. Has some pre-added elements for demonstration purposes **/
        @SuppressWarnings("serial")
        final MyList<Object> customObject3 = new MyList<Object>() {{
            add(1);
            add(2);
        }};
        /** The list that will contain all desired data.It'll be converted to a SHA-256 string later**/
        @SuppressWarnings("serial")
        final List<Object> list = new ArrayList<Object>() {
            @Override
            public int hashCode () {
                return Local.Fields.expectedOverridenHashCode;
            }
            {
                add(customObject1);
                add(customObject2);
                add(customObject3);
            }
        };
        /* Actual output */
        out.println(Local.Methods.generateSHA256(list));
    }
}
