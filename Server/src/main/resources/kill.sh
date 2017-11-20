echo 'kill running application'
kill $(ps aux | grep -F BibliothekWS2017Server | grep -v -F 'grep' | awk '{ print $2 }') || true