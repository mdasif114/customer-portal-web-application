This project can be started either directly from command line or by IDE.
 
1)  ## Maven command execution
        * Execute "mvn spring-boot:run" command from command line to launch the application

    ## IDE excution
        * Run CustomerPortalApplication.java file to launch the application

2) By default, application will be started on 8080 port. 
    If you wish to start on different port, configure "server.port=<port-value>" in application.properties file.
    Ex: server.port=8080

3) Access URL, http://localhost:8080 from browser to launch the application.

3) To access H2 database, just append "h2-console" to the end of URL
    Ex: http://localhost:8080/h2-console/

