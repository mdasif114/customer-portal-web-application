#### Hope you have already installed & configured PATH environmental  variables for, 
    * Java 8+
    * Maven 3

#### How to launch & Run this application ?
It's quite easy. 

##### 1) Start the application by using maven command or IDE tool. 
###### Option:1. Maven command (recommended)

* Open command prompt and enter into the main directory of this application (where **pom.xml** file is located), then execute below command

        mvn spring-boot:run

###### Option:2. Using Eclipse (or) IntelliJ
* If you're using eclipse, you need to install & configure STS plugin before starting this application.
* if you're using IntelliJ community edition, then you have to configure main class in maven settings to start the application.

##### 2) By default, application will be started on 8080 port. 
    If you wish to start on different port, then you need to configure "server.port=<port-value>" in application.properties file.
    
    Example: server.port=8080

##### 3) Access URL, http://localhost:8080 from browser then you will see login page.

##### 4) To access H2 database, just append "h2-console" to the end of application URL
    Example: http://localhost:8080/h2-console/

