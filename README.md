# # java-rest-api
## # Description #
A springboot rest api for users and organizations with many to many relationships.

1. **Setup**
	1. Cloning the repository with git.
		1. `git clone https://github.com/ssulkar/java-rest-api.git)`
	2. Create a local MySQL database.
		1. Install MySQL.
		2. Log into MySQL and create a database.
		1. `mysql -u root -p`
		2. `create database YOURDATABASENAME;`
    	3. Update java-rest-api/demo/src/main/resources/application.properties with your database info.
      	1. 'spring.datasource.url=jdbc:mysql://localhost:3306/YOURDATABASENAME?useSSL=false
         spring.datasource.username=root
         spring.datasource.password=password`
	3. Install Postman to test endpoints.
2. **Running**
	1. Run the application file java-rest-api/demo/src/main/java/com/example/demo/DemoApplication.java in Intellij.
3. **API Documentation**
	1. Method: post
		1. request url: */users*
		2. request url: */organizations*
	2. Method: get
		1. request url: */users*
		2. request url: */users/{userId}*
		3. request url: */users/{userId}/organizations*
		4. request url: */organizations*
		5. request url: */organizations/{organizationId}*
		6. request url: */organizations/{organizationId}/users*
	3. Method: put
		1. request url: */organizations/{organizationId}/users/{userId}*
	4. Method: delete
		1. request url: */users/{userId}*
		2. request url: */organizations/{organizationId}*
		3. request url: */organizations/{organizationId}/users/{userId}*
4. Examples:
