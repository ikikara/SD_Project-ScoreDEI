# SD_Project-ScoreDEI
- [x] Finished

## Index
- [Description](#description)
- [Technologies used](#technologies-used)
- [To run this project](#to-run-this-project)
- [Notes important to read](#notes-important-to-read)
- [Authors](#authors)

## Description
This project was developed for Distributed Systems subject @University of Coimbra, Informatics Engineering <br>
Consists in develop a program that will host a "website" to see info about football games using Thymeleaf, Spring-boot, JPA queries and others elements.

#### Main Languages:
![](https://img.shields.io/badge/Java-333333?style=flat&logo=java&logoColor=FFFFFF) 
![](https://img.shields.io/badge/HTML-333333?style=flat&logo=html5&logoColor=E67925)

## Technologies used:
1. Java
    - [Version ??](https://www.oracle.com/java/technologies/downloads/) 
2. Spring-boot 
3. [Maven](https://maven.apache.org/download.cgi)
4. Thymeleaf
5. [PgAdmin](https://www.pgadmin.org/download/)

## To run this project:
[WARNING] Java and Maven must be installed<br>
After installing PgAdmin:
1. Create a database with name "ScoreDei"<br>
![image](https://i.imgur.com/mLPZupa.png)
![image](https://i.imgur.com/W4bPXKg.png)
2. Configure your connection to the database changing some fields in application.properties file
   ![image](https://i.imgur.com/eR987OU.png)
   
   NOTE: this file is in following directory: [your directory]/src/main/resources
3. Finally just run it
    ```shellscript
    [your-disk]:[name-path]> mvnw spring-boot:run
    ```
    Access the website:
    ![image](https://i.imgur.com/SZR3rpn.png)
    

## Notes important to read
- For more information about the project, end-points and links read the Report
- The function "edit-team" doesn't work properly
- When run for the first time can give some errors, in this case delete 5th line of application.properties file and change the field on 8th line to *create* instead of *update*. Then for 2nd and following runs change to the original configurations

## Authors:
- [João Silva](https://github.com/ikikara)
- [Mário Lemos](https://github.com/MrMarito) 
- [Pedro Martins](https://github.com/PedroMartinsUC) 
