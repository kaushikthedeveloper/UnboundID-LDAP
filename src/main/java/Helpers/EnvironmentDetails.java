package Helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ---Helper class---
 * Provide the data given in resources/environment.properties
 */
public class EnvironmentDetails {

    // filepath
    private String f_environment = "../environment.properties";

    private String serverAddress;
    private int portNumber;
    private String bindDN;
    private String password;

    /**
     * Load the details from environment.properties
     * Initalise the class members
     */
    public EnvironmentDetails(){
        // set the Properties file
        Properties prop = new Properties();
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(f_environment);
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get the properties
        serverAddress = prop.getProperty("serverAddress");
        bindDN = prop.getProperty("bindDN");
        password = prop.getProperty("password");
        portNumber = Integer.parseInt( prop.getProperty("portNumber") );
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getBindDN() {
        return bindDN;
    }

    public String getPassword() {
        return password;
    }
}
