/opt/glassfish5/glassfish/bin/asadmin --user admin --passwordfile /opt/glassfish5/glassfish/bin/password.txt undeploy BibliothekWS2017Server || true
mv ./Server/target/BibliothekWS2017Server-1.0-SNAPSHOT-jar-with-dependencies.jar ./Server/target/BibliothekWS2017Server.jar'
/opt/glassfish5/glassfish/bin/asadmin --user admin --passwordfile /opt/glassfish5/glassfish/bin/password.txt deploy ./Server/target/BibliothekWS2017Server.jar
