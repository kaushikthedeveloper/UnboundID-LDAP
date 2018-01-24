import javax.net.ssl.SSLSocketFactory;

import Helpers.ConfigDetails;
import Helpers.EnvironmentDetails;
import com.unboundid.ldap.sdk.*;
import com.unboundid.util.ssl.SSLUtil;
import com.unboundid.util.ssl.TrustAllTrustManager;

/**
 * UnboundID LDAP SDK for Java
 *
 * Search Operation
 * - establish connection
 * - query the ldap server
 *
 * Requires :
 * - Server Address
 * - Port Number
 * - Bind DN
 * - Password
 * - Search Query
 */
public class SearchLDAP {
    public static void main(String[] args) {

        // to access the properties
        EnvironmentDetails environmentDetails = new EnvironmentDetails();
        ConfigDetails configDetails = new ConfigDetails();

        // local variables
        // connection - environment
        String serverAddress = environmentDetails.getServerAddress();
        int portNumber = environmentDetails.getPortNumber();

        // credentials
        String bindDN = environmentDetails.getBindDN();
        String password = environmentDetails.getPassword();

        try {
            // Create an SSLUtil instance that is configured to trust any certificate,
            // and use it to create a socket factory.
            SSLUtil sslUtil = new SSLUtil(new TrustAllTrustManager());
            SSLSocketFactory sslSocketFactory = sslUtil.createSSLSocketFactory();

            // Establish a secure connection using the socket factory.
            LDAPConnection connection = new LDAPConnection(sslSocketFactory);
            connection.connect(serverAddress, portNumber);
            connection.bind(bindDN, password);

            System.out.println("Connected at : " + bindDN);

            // // // // Search Operation // // // //
            // Search parameters
            String toSearchBaseDN = configDetails.getToSearchBaseDN();
            String toSearchAttribute = configDetails.getToSearchAttribute();

            // Perform a search to retrieve the attributes in the LDAP
            try {
                SearchRequest searchRequest = new SearchRequest(
                        toSearchBaseDN,                                     // Base DN
                        SearchScope.BASE,                                   // Scope  (change according to need)
                        Filter.createPresenceFilter("objectClass"),         // Filter (change according to need)
                        toSearchAttribute                                   // Attributes to return
                );

                // result
                SearchResultEntry searchResult =
                        connection.searchForEntry(searchRequest);

                // get required attributes
                Attribute searchAttribute =
                        searchResult.getAttribute(toSearchAttribute);

                // print the Search Result returned
                System.out.println("\n----------------------------------\n");
                System.out.println("Result : " + searchResult);

                System.out.println("\n----------------------------------\n");
                // go through all(1 / >1) the returned attribute values
                for(String val: searchAttribute.getValues()){
                    System.out.println("Result Values : "+val);
                }

            }catch (LDAPSearchException lse)
            {
                // The search failed for some reason.
                SearchResult searchResult = lse.getSearchResult();
                ResultCode resultCode = lse.getResultCode();
                String errorMessageFromServer = lse.getDiagnosticMessage();

                lse.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
