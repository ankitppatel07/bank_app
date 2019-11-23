# Banking Application using Spring Framework

## Description:
- Developed an Application to create bank account and perform all banking operations
- Integrated data using DAO Support classes in Spring.
- Established database connection to a light-weight Apache Derby database with the help of JDBC data access in Spring Framework
- Implemented connection pooling using Apache Commons DBCP

## Instructions:
- The repo consists of .sql files in data folder that consists of all the data and tables needed to create a database in Apache Derby.
- This is a Maven project. So there is no need to download external .jars

## Steps to setup the Database: 
- To setup Apache Derby Database, download the bin distribution from https://db.apache.org/derby/derby_downloads.html
- Extract and copy derby.jar and derbyclient.jar from the lib folder and add it to the project.
- Open command prompt in bin folder
- type > startNetworkServer.bat
- Open new command prompt in bin folder
- type > ij.bat
The below command connects to OR creates a Database if not present:
- type > connect 'jdbc:derby://localhost:1527/db;create=true';
- run the SQL commands present in the data folder of my repository
To come out of the client:
- type > exit;

### Tech Stack: Spring Core, Java, SQL, JDBC, Apache Derby, Maven, Eclipse IDE
