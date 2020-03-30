# Private School Spring Boot CRUD with AJAX

This project is a basic CRUD (Create, Read, Update, Delete) Spring Boot implementation of the Trainers Entity 
based on previously developed [Private School project](https://github.com/nikossyr/PrivateSchoolStructureDBConnection). 
It comprises of a Frontend (View) that enables the user to make basic data manipulations of a Database table, 
a Backend responsible for handling the user actions (Controller) and accomplishing data manipulation in a 
persistence unit (Model). It is worth noting that from the users point of view all manipulation is being done by the same
url in an intuitive interface.  

## Getting Started

In order to run the project you can use any of the wildly used IDEs (Netbeans, IntelliJ, Ecliple) to compile and run.
The project is written in Java 8. The application works with MySQL Database.
 

#### Prerequisites
* JRE v8
* JDK v8
* Database with a Trainer table. A MySQL script for creating as such with mock data, is provided in folder 
/MySQL-DB-scripts/spring_crud_trainers_SCRIPT.sql **
* The database connection credentials should be set on  src/main/resources/application.properties
* Once downloaded build the file with dependencies and let maven download whatever needed. 
* Recommended: IDE (Netbeans, IntelliJ, Ecliple)

** In order to avoid creating a new Database table, the web app can be tested using an in-memory database such as Apache Derby. 
A list of in-memory databases and how to implement can be found [here](https://www.baeldung.com/java-in-memory-databases).

## Built With

#### Backend
* [Spring Boot](https://spring.io/projects/spring-boot) - The back end framework user
  * Spring Web
  * Spring Data JPA + MySQL Driver 
* [Maven](https://maven.apache.org/) - The Dependency Management tool used
  
#### Frontend
* [jQuery](https://https://jquery.com/) - The JS framework used
* [AJAX](https://api.jquery.com/jquery.ajax/) - The jQuery library used for Asynchronous Http Requests
* [Bootstrap](https://https://getbootstrap.com/) - The CSS framework used
* [JSP](https://docs.oracle.com/javaee/5/tutorial/doc/bnagy.html) + HTML


