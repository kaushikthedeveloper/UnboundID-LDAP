# Java project for LDAP operations using UnboundID SDK

This project focuses on providing complimentary example codes to the operations possible with UnboundID Java SDK in 
accessing LDAP server.

Recommended to first go through : 
- [Basics of LDAP](https://www.ldap.com/getting-started-with-ldap)
- [Understanding LDAP Schema](https://www.ldap.com/understanding-ldap-schema)
- [LDAP Operation Types](https://www.ldap.com/ldap-operation-types)
- [UnboundID LDAP SDK for Java](https://www.ldap.com/unboundid-ldap-sdk-for-java)

## Steps for experimenting in the project

### LDAP server

1. Have a LDAP server setup for the experimentation (in the project SSL protocol is followed).

### Java project

2. In the [`environment.properties`](
https://github.com/kaushikthedeveloper/UnboundID-LDAP/blob/master/src/main/resources/environment.properties), specify 
the environment variables that are to be used in establishing the connection with the LDAP.

    ```
    serverAddress = <ldap_server_address>
    portNumber = <ldap_port_number>
    bindDN = <bind_dn>
    password = <password>
    ```

3. In the [`config.properties`](
https://github.com/kaushikthedeveloper/UnboundID-LDAP/blob/master/src/main/resources/config.properties), specify 
the variables that are to be used for other purposes across the project.

    Example, in the [SearchLDAP class](
https://github.com/kaushikthedeveloper/UnboundID-LDAP/blob/master/src/main/java/SearchLDAP.java), the following 
configs are used : 

    ```
    toSearchBaseDN = <base_dn_which_is_to_be_queried>
    toSearchAttribute = <search_attribute_which_is_to_be_queried>
    ```

4. Proceed with the Java programs.


### Downloading the UnboundID SDK

Using the Maven repo, include the following in the `pom.xml` :

```xml
<!-- https://mvnrepository.com/artifact/com.unboundid/unboundid-ldapsdk -->
<dependency>
    <groupId>com.unboundid</groupId>
    <artifactId>unboundid-ldapsdk</artifactId>
    <version>RELEASE</version>
</dependency>

```


## Other Reading Materials
- [UnboundID LDAP SDK for Java](
https://docs.ldap.com/ldap-sdk/docs/javadoc/overview-summary.html)
- [Performing LDAP Operations](https://docs.ldap.com/ldap-sdk/docs/getting-started/operations.html)

### Project based
#### ConnectLDAP :
- [Creating and Using LDAP Connections](https://docs.ldap.com/ldap-sdk/docs/getting-started/connections.html)
- [Class SSLUtil](https://docs.ldap.com/ldap-sdk/docs/javadoc/com/unboundid/util/ssl/SSLUtil.html)

#### SearchLDAP :
- [Using ldapsearch](
https://www.centos.org/docs/5/html/CDS/ag/8.0/Finding_Directory_Entries-Using_ldapsearch.html)
- [Class LDAPSearch](
https://docs.ldap.com/ldap-sdk/docs/javadoc/com/unboundid/ldap/sdk/examples/LDAPSearch.html)