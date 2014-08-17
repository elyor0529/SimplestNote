package utils;

import java.util.ResourceBundle;

/**
 * Created by Elyor on 8/10/2014.
 */

public class Settings {

    //resource
    public final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("Resource.properties");

    //authorize
    public static final String AUTHORIZE_KEY = "_AUTHORIZE_KEY_";

    //rest
    public static final String REST_TYPE = "application/json";

    public static final class PAGING {
        public static final int LIMIT = 25;
        public static final int OFFSET = 0;
    }

}
