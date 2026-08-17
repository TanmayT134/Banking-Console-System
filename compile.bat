@echo off
setlocal
if not exist out mkdir out
set "CP=lib\javax.mail-1.6.2.jar;lib\activation-1.1.1.jar"
javac -cp "%CP%" -d out src\com\tanmay\corebanking\Main.java src\com\tanmay\corebanking\enums\*.java src\com\tanmay\corebanking\exception\*.java src\com\tanmay\corebanking\model\*.java src\com\tanmay\corebanking\repository\*.java src\com\tanmay\corebanking\service\*.java src\com\tanmay\corebanking\ui\*.java src\com\tanmay\corebanking\util\*.java src\com\tanmay\corebanking\test\*.java
if errorlevel 1 exit /b 1
echo Compilation successful.
