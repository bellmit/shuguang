@echo off 
   java -cp  ./lib/jasypt-1.9.2.jar  org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI algorithm=PBEWithMD5AndDES  password=sofn@2021  input=%1   
    