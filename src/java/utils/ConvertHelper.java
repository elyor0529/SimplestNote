package utils;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Elyor on 8/18/2014.
 */
public final class ConvertHelper {

    public static final String ToString(Object o) {
        return o != null ? o.toString() : "";
    }

    private static final String ToString(String s) {
        return s != null ? s : "";
    }

    public static final Date ToDate(Object o) {
        return ToDate(ToString(o));
    }

    private static final Date ToDate(String s) {

        if (s.isEmpty())
            return null;

        Date dt;
        try {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            dt = dateFormat.parse(s);
        } catch (Exception e) {
            dt = null;
        }

        return dt;
    }

    public static final Integer ToInteger(String s) {

        if (ToString(s).isEmpty()) {
            return null;
        }

        return Integer.parseInt(ToString(s));
    }

    public static final Integer ToInteger(Objects o) {
        return ToInteger(ToString(o));
    }

    private static final BigInteger ToBigInteger(String s) {

        if (ToString(s).isEmpty()) {
            return null;
        }

        return new BigInteger(ToString(s));
    }

    public static final BigInteger ToBigInteger(Object o) {
        return ToBigInteger(ToString(o));
    }

    public static final Long ToLong(String s) {

        if (ToString(s).isEmpty()) {
            return null;
        }

        return Long.parseLong(ToString(s));
    }

    private static final Long ToLong(Objects o) {
        return ToLong(ToString(o));
    }


}
