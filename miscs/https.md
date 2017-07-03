#####Password: changeme

- Create a self signed certificate

        $ openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout localhost_self-signed.key -out localhost_self-signed.pem

- create a .p12 file (that can be imported and trusted within OS X’s Keychain application for example):

        $ openssl pkcs12 -export -in ./ssl/localhost_self-signed.pem -inkey ./ssl/localhost_self-signed.key -name “SelfSignedServer” -out localhost_self-signed.p12

- Create the directory structure and initial stuff

        $ mkdir ./ssl
        $ cd ./ssl
        $ mkdir certs private
        $ echo ‘100001’ > serial
        $ touch certindex.txt
        $ vi openssl.cnf

-   Create a CA by creating a root certificate.
    This will create the private key (private/cakey.pem) and the public key (cacert.pem, a.k.a. the certificate) of the root CA.

        $ openssl req -new -x509 -extensions v3_ca -keyout private/cakey.pem -out cacert.pem -days 365 -config ./openssl.cnf

-   Create the Server cert.
    Create a key and signing request for the server. This will create the CSR for the server (server-req.pem) and the server’s private key (private/server-key.pem).

        $ openssl req -new -nodes -out server-req.pem -keyout private/server-key.pem -days 365 -config openssl.cnf

-   CA sign the server’s CSR. This will create the server’s public certificate (server-cert.pem)

        openssl ca -out server-cert.pem -days 365 -config openssl.cnf -infiles server-req.pem

-   Create the Client cert
    Each client will create a key and signing request.
    common name = client
    This will create the client’s CSR (client-req.pem) and the client’s private key (private/client-key.pem)

        $ openssl req -new -nodes -out client-req.pem -keyout private/client-key.pem -days 365 -config openssl.cnf

-   Have the CA sign the client’s CSR. This will create the client’s public certificate (client-cert.pem)

        $ openssl ca -out client-cert.pem -days 365 -config openssl.cnf -infiles client-req.pem

-   Create the PKCS12 file using the client’s private key, the client’s public cert and the CA cert. This will create the (Mac-friendly) PKCS12 file (client-cert.p12)

        $ openssl pkcs12 -export -in client-cert.pem -inkey private/client-key.pem -certfile cacert.pem -name "Client" -out client-cert.p12

-   (Mac) Install the CA cert into Keychain Access

    - Open ./ssl in Finder:
    - Open the CA cert (cacert.pem) by double-clicking it to install it to Keychain Access
    - Mark it as trusted
    - Open your browser to https://localhost and you should see a successful secure connection (Apache HTTP)


-   OpenSSL can now confirm that the two-way SSL handshake can be successfully completed

        openssl s_client -connect localhost:443 -tls1 -cert ./ssl/client-cert.pem -key ./ssl/private/client-key.pem

-   (Mac) Install the client PKCS12 file into Keychain Access

    - Open ./ssl in Finder and double-click the client PKCS12 file (client-cert.p12) to install it to Keychain Access
    - Open https://localhost in your browser again and select the client cert when prompted

-   cURL will also work

        $ curl -v --cert ./ssl/client-cert.p12:changeme https://localhost

-   Generate a PKCS12 (.p12) file from the public server-cert.pem, the private server-key.pem, and the CA cert cacert.pem

        $ openssl pkcs12 -export -in ./ssl/server-cert.pem -inkey ./ssl/private/server-key.pem -certfile ./ssl/cacert.pem -name "Server" -out server-cert.p12

        If you see unable to write 'random state', run sudo rm ~/.rnd and try again

-   Java KeyStores (JKS)

    Java has its own version of PKCS12 called Java KeyStore (JKS). It is also password protected. Entries in a JKS file must have an “alias” that is unique. If an alias is not specified, “mykey” is used by default. It’s like a database for certs and keys.

    - KeyStores provide credentials
    - TrustStores verify credentials

    Clients will use certificates stored in their TrustStores to verify identities of servers. They will present certificates stored in their KeyStores to servers requiring them.

    The JDK ships with a tool called **_`Keytool`_**. It manages a JKS of cryptographic keys, X.509 certificate chains, and trusted certificates.


- Create the Server’s KeyStore from the PKCS12 file
    
        $ keytool -importkeystore -deststorepass changeme -destkeypass changeme -destkeystore server_keystore.jks -srckeystore server-cert.p12 -srcstoretype PKCS12 -srcstorepass changeme -alias server

- View the server keystore to confirm it now contains the server’s cert

        $ keytool -list -v -keystore server_keystore.jks

- Create the client’s truststore and import the server’s public certificate

        $ keytool -import -v -trustcacerts -keystore client_truststore.jks -storepass changeme -alias server -file ./ssl/server-cert.pem

- View the client’s truststore to confirm it contains the server’s cert

        $ keytool -list -v -keystore client_truststore.jks

#####Two-Way SSL (Client Certificates)
To configure two-way SSL we have to create the server’s truststore and create the client’s keystore.

- Since the client’s certificate is signed by a CA we created ourselves, import the CA cert into the server truststore

        $ keytool -import -v -trustcacerts -keystore server_truststore.jks -storepass changeme -file ./ssl/cacert.pem -alias cacert

- Viewing the server truststore will show the CA’s certificate

        $ keytool -list -v -keystore server_truststore.jks

- Finally, the client keystore stores the client certificate that will presented to the server for SSL authentication. Import the cert from client’s PKCS12 file (created above)

        $ keytool -importkeystore -srckeystore ./ssl/client-cert.p12 -srcstoretype pkcs12 -destkeystore client_keystore.jks -deststoretype jks -deststorepass changeme

- Viewing the created client_keystore.jks file will show the client entry in the keystore

        $ keytool -list -v -keystore client_keystore.jks


#####Documentation:

   http://www.robinhowlett.com/blog/2016/01/05/everything-you-ever-wanted-to-know-about-ssl-but-were-afraid-to-ask/

   https://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-servlet-containers.html#howto-configure-ssl

   http://www.baeldung.com/spring-security-custom-filter?utm_content=buffer76043&utm_medium=social&utm_source=twitter.com&utm_campaign=buffer

#####Examples:

   https://github.com/robinhowlett/everything-ssl