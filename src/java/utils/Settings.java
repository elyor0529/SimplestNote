package utils;

import java.util.ResourceBundle;

/**
 * Created by Elyor on 8/10/2014.
 */

public class Settings {

    //resource
    public final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("Resource.properties");

    //authorize
    public final static int SESSION_IN_ACTIVE_INTERVAL = 86400;
    public static final String SESSION_AUTHORIZE_KEY = "_AUTHORIZE_KEY_";

    //rest
    public static final String REST_TYPE = "application/json";

    public static final class PAGING {
        public static final int PAGE = 1;
        public static final int COUNT = 10;
        public static final int LIMIT = 25;
        public static final int OFFSET = 0;
    }

}
