package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Elyor on 8/18/2014.
 */
public final class ConvertHelper {
    public static final String ToString(Object o) {
        return o != null ? o.toString() : "";
    }

    public static final String ToString(String s) {
        return s != null ? s : "";
    }

    public static final Date ToDate(Object o) {
        final String s = ToString(o);

        return ToDate(s);
    }

    public static final Date ToDate(String s) {
        Date dt;

        try {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            dt = dateFormat.parse(s);
        } catch (Exception e) {
            dt = null;
        }

        return dt;
    }


}
