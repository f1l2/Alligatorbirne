call "startCM.bat"
TIMEOUT 1
call "startEP"
TIMEOUT 15
call java -jar monitoring-client-1.0.0-SNAPSHOT.jar script test2-script.txt
TIMEOUT 1
call "startIoTD"
