import javax.net.ssl.SSLSocketFactory;

import Helpers.EnvironmentDetails;
import com.unboundid.ldap.sdk.BindResult;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.util.ssl.SSLUtil;
import com.unboundid.util.ssl.TrustAllTrustManager;

/**
 * UnboundID LDAP SDK for Java
 *
 * Connect to LDAP using SSL protocol
 * - uses TrustAllTrustManager
 *
 * Requires :
 * - Server Address
 * - Port Number
 * - Bind DN
 * - Password
 */
public class ConnectLDAP {

    public static void main(String[] args) {

        // to access the properties
        EnvironmentDetails environmentDetails = new EnvironmentDetails();

        // local variables
        // connection - environment
        String serverAddress = environmentDetails.getServerAddress();
        int portNumber = environmentDetails.getPortNumber();

        // credentials
        String bindDN = environmentDetails.getBindDN();
        String password = environmentDetails.getPassword();

        try {
            // Create an SSLUtil instance that is configured to trust any certificate,
            // Use it to create a socket factory
            SSLUtil sslUtil = new SSLUtil(new TrustAllTrustManager());
            SSLSocketFactory sslSocketFactory = sslUtil.createSSLSocketFactory();

            // Establish a secure connection using the socket factory
            LDAPConnection connection = new LDAPConnection(sslSocketFactory);
            connection.connect(serverAddress, portNumber);
            connection.bind(bindDN, password);

            // Connection established
            System.out.println("Connected at : " + bindDN);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}