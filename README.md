# Spring-Boot-Company
Spring Boot Crud application made using Thymeleaf, Spring MVC.

# Company System
This project is based on the Spring Boot and used the following technologies:

* Spring Boot
* Spring MVC
* Spring Data Jpa
* MySql Database
* Thymeleaf for template engine
* Maven
* JavaScrip

## Installation

1. Clone the application:
<pre> $ git clone https://github.com/MatheusRGuedes/spring-boot-company.git </pre>

2. Database Configuration
This project uses the MySql database, so follow these steps:
* Open the application.properties file in the src/main/resources/ directory;
* If you run the application locally, change <code>spring.datasource.username</code> and <code>spring.datasource.password</code> according to what you defined on the MySql installation;
* Insert scripts for the tables are in the data folder.

3. Run
Maven is used for project management and to run the application locally, use the commands:
<pre>mvn clean install</pre>
<pre>
mvn spring-boot: run
or
java -jar demo-mvc-0.0.1-SNAPSHOT.jar
</pre>

The commands above will generate the package, install the dependences and execute the application on 8080 default port.
The application runs from <a href="http://localhost:8080/">http://localhost:8080/</a>.

## Screenshots
1. Root Application Page
![homepage](https://user-images.githubusercontent.com/48195358/95787690-6a2c5600-0cb0-11eb-9e70-522483e4bea7.png)

2. Some registration page
![cadastro-funcionarios](https://user-images.githubusercontent.com/48195358/95787368-c347ba00-0caf-11eb-858b-842362009a2b.png)

3. Some list page
![listagem-cargos](https://user-images.githubusercontent.com/48195358/95787227-706e0280-0caf-11eb-9bdb-b63d78b947a1.png)

4. Errors Pages
![page404 (2)](https://user-images.githubusercontent.com/48195358/95785944-12d8b680-0cad-11eb-9f37-4c7b6beca14c.png)
![page500](https://user-images.githubusercontent.com/48195358/95786011-33a10c00-0cad-11eb-9028-f5b56a7632e1.png)