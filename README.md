# To run the application in local
    1. Clone the repository or download the project from github.
    2. Open the project in IDE
    3. Setup the application run configurations
    4. Run the project via ide or command line

# To set up the database
    1. Run the application in local
    2. Go  the url in browser (http://localhost:8080/h2-console) and login to db using below credentials(refer application.yml datasource configuration)
        jdbc url: jdbc:h2:mem:mydb
        username: sa
        password: password
    3. Run the below query to set my conference room details

        Insert into conference_room (id, name, capacity ,description) values (1,'Amaze',3,'3 people capacity');
        Insert into conference_room (id, name, capacity ,description) values (2,'Beauty',7,'7 people capacity');
        Insert into conference_room (id, name, capacity ,description) values (3,'Inspire',12,'12 people capacity');
        Insert into conference_room (id, name, capacity ,description) values (4,'Strive',20,'20 people capacity');

# Api details
    1. Swagger url: http://localhost:8080/swagger-ui/index.html
    2. Open api docs in json: http://localhost:8080/v3/api-docs

# Note
    1. Both the apis accepts the time as string in 24 hr format 
        eg: 08:00 - 8 am
            14:00 - 2 pm
    2. Start time should be always less than end time

