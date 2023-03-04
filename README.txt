Approach:
The solution to create an endpoint for a new Role service was implemented by creating two tables for the requested functions: "membership" and "role_membership". The "Membership" table, as already known, contained the records of the relationship between a user and a team. The "role_membership" table contains the records of the relationship between a user, the team they are part of, and their role in that team.

The application contains four modules:

"domain": which contains the entities Role, User, Team, RoleMembership, and Membership.
"service-impl": which contains the services + implementations, repositories, and tests of the services and implementations.
"user": contains DTOs, Services, DAO, etc., and tests that will be suitable only for the User class.
"team": contains DTOs, Services, DAO, etc., and tests that will be suitable only for the Team class.
For testing:

As the system grew, I was unable to create tests for all the necessary classes and without enough time to verify the test coverage of each class. I created only one test class using Groovy+Spock+Mockito, which was performed in the implementation class of the Role service. And another test class using JUnit + Mockito for the Role service class.
Database:
MySQL 8.0 was used to simulate the CRUD of the project.

Swagger:
The Open API 3.0 library from Spring itself was used. To access it, just go to the system at:
http://localhost:8081/swagger-ui/index.html#/ for the Role endpoints
http://localhost:8082/swagger-ui/index.html#/ for the Team endpoints
http://localhost:8083/swagger-ui/index.html#/ for the User endpoints.

Or you can import the file e-Core.postman_collection.json (main folder) and test on Postman.

Configuration:
To start the system, just import the uncompressed file as a Gradle project (I used IntelliJ for development). Since the file used a local database, it is recommended to install it on the machine or use the image through docker:

css
Copy code
docker run -p 3308:3306 --name mysql -e MYSQL_ROOT_PASSWORD='!@#M3t4tr0n' -e MYSQL_USER=root -e MYSQL_PASSWORD='!@#M3t4tr0n' -d mysql:8.0
After initializing the database, three (3) modules must be started, with the Role module always being the first, as this module will populate the database tables.

Improvements in the project:

I did not implement the solution to check if there is already a Team Leader in that team. This would be done in the RoleMembershipServiceImpl class.
For the Team and User services, I implemented some endpoints with functions that would be good options to be implemented.