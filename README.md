# java-rest-api
Description
A springboot rest api for users and organizations with many to many relationships.

1. Setup
  A. Cloning the repository with git.
    i. "git clone https://github.com/ssulkar/java-rest-api.git"
  B. Create a local MySQL database.
    i. Install MySQL.
      a.
    ii. Log into MySQL and create a database.
      a. "mysql -u root -p"
      b. "create database YOURDATABASENAME;"
    iii. Update java-rest-api/demo/src/main/resources/application.properties with your database info.
      a. spring.datasource.url=jdbc:mysql://localhost:3306/YOURDATABASENAME?useSSL=false
         spring.datasource.username=root
         spring.datasource.password=password
    iv. Install Postman to test endpoints.
2. Running
  A. Run the application file java-rest-api/demo/src/main/java/com/example/demo/DemoApplication.java in Intellij.
3. API Documentation
  A. Method: post
    1. request url: "/users"
    2. request url: "/organizations"
  B. Method: get
    1. request url: "/users"
    2. request url: "/users/{userId}"
    3. request url: "/users/{userId}/organizations"
    4. request url: "/organizations"
    5. request url: "/organizations/{organizationId}"
    6. request url: "/organizations/{organizationId}/users"
  C. Method: put
    1. request url: "/organizations/{organizationId}/users/{userId}"
  D. Method: delete
    1. request url: "/users/{userId}"
    2. request url: "/organizations/{organizationId}"
    3. request url: "/organizations/{organizationId}/users/{userId}"
4. Examples:
