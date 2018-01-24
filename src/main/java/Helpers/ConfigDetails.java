package Helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ---Helper class---
 * Provide the data given in resources/config.properties
 */
public class ConfigDetails {

    // filepath
    private String f_config = "../config.properties";

    private String toSearchBaseDN;
    private String toSearchAttribute;

    /**
     * Load the details from environment.properties
     */
    public ConfigDetails(){
        // set the Properties file
        Properties prop = new Properties();
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(f_config);
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get the properties
        toSearchBaseDN = prop.getProperty("toSearchBaseDN");
        toSearchAttribute = prop.getProperty("toSearchAttribute");
    }

    public String getToSearchBaseDN() {
        return toSearchBaseDN;
    }

    public String getToSearchAttribute(){
        return toSearchAttribute;
    }
}
