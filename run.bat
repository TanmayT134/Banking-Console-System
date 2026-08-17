@echo off
call compile.bat
if errorlevel 1 pause & exit /b 1
java -cp "out;lib\javax.mail-1.6.2.jar;lib\activation-1.1.1.jar" com.tanmay.corebanking.Main
