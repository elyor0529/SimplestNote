package utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Elyor on 8/11/2014.
 */

public class StringGenerator {

    public static String generateString(int length, Mode mode) {

        StringBuffer buffer = new StringBuffer();
        String characters = "";

        switch (mode) {

            case ALPHA:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                break;

            case ALPHANUMERIC:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                break;

            case NUMERIC:
                characters = "1234567890";
                break;
        }

        int charactersLength = characters.length();
        Random random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            double index = random.nextInt(charactersLength);
            buffer.append(characters.charAt((int) index));
        }

        return buffer.toString();
    }

    public static enum Mode {
        ALPHA,
        ALPHANUMERIC,
        NUMERIC
    }
}

