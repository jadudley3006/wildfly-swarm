package common;

import java.util.Base64;

public class Base64Util {

    public static String base64Encode(String str) {
        byte[] bytes = str.getBytes();
        return new String(Base64.getEncoder().encode(bytes));
    }

    public static String base64Decode(String str) {
        byte[] bytes = str.getBytes();
        return new String(Base64.getDecoder().decode(bytes));
    }

}
