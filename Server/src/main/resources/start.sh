kill -9 $(ps aux | grep -F BibliothekWS2017Server | grep -v -F 'grep' | awk '{ print $2 }')
nohup java -jar ./Server/target/BibliothekWS2017Server-1.0-SNAPSHOT-jar-with-dependencies.jar &