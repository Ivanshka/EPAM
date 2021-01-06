cls
mvn -Dbrowser=firefox -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testng-smoke.xml clean test
pause