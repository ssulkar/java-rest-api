# # java-rest-api
## # Description #
A springboot rest api for users and organizations with many to many relationships.

1. **Setup**
	1. Cloning the repository with git.
		1. `git clone https://github.com/ssulkar/java-rest-api.git`
	2. Create a local MySQL database.
		1. Install MySQL.
		2. Log into MySQL and create a database.
		1. `mysql -u root -p`
		2. `create database YOURDATABASENAME;`
	3. Update java-rest-api/demo/src/main/resources/application.properties with your database info.
		1. spring.datasource.url=jdbc:mysql://localhost:3306/YOURDATABASENAME?useSSL=false
         	spring.datasource.username=root
         	spring.datasource.password=password
	4. Install Postman to test endpoints.
2. **Running**
	1. Run the application file java-rest-api/demo/src/main/java/com/example/demo/DemoApplication.java in Intellij.
3. **API Documentation**
	1. Method: post
		1. */users*
		2. */organizations*
	2. Method: get
		1. */users*
		2. */users/{userId}*
		3. */users/{userId}/organizations*
		4. */organizations*
		5. */organizations/{organizationId}*
		6. */organizations/{organizationId}/users*
	3. Method: put
		1. */organizations/{organizationId}/users/{userId}*
	4. Method: delete
		1. */users/{userId}*
		2. */organizations/{organizationId}*
		3. */organizations/{organizationId}/users/{userId}*
4. Examples:
	1. Method: post
		1. *localhost:8080/users*
			1. {
			"first": "elon", 
			"last": "musk", 
			"email": "elon@spacex.com", 
			"address": "los angeles", 
			"phone": "1234567890"
			}
			2. This request adds a new user to the database.
		2. *localhost:8080/organizations*
			1. {
			"name": "spacex"
			"address": "los angeles"
			"phone": "1234567890"
			}
			2. This request adds a new organization to the database.
	2. Method: get
		1. *localhost:8080/users*
			1. No body.
			2. This request gets all users in the database.
		2. *localhost:8080/users/1*
			1. No body but the request requires a user id.
			2. This request gets information about a specific user from the database.
		3. *localhost:8080/users/1/organizations*
			1. No body but the request requires a user id.
			2. This request gets all organizations a specific user is associated with.
		4. *localhost:8080/organizations*
			1. No body.
			2. This request gets all organizations from the database.
		5. *localhost:8080/organizations/1*
			1. No body but the request requires an organization id.
			2. This request gets information about a specific organization from the database.
		6. *localhost:8080/organizations/1/users*
			1. No body but the request requires an organization id.
			2. This request gets all users a specific organization is associated with.
	3. Method: put
		1. *localhost:8080/organizations/1/users/1*
			1. No body but this request requires an organization id and a user id.
			2. This request adds a specific user to a specific organization.
	4. Method: delete
		1. *localhost:8080/users/1*
			1. No body but this request requires a user id.
			2. This request deletes a specific user from the database.
		2. *localhost:8080/organizations/1*
			1. No body but this request requres an organization id.
			2. This request deletes a specific organization from the database.
		3. *localhost:8080/organizations/1/users/1*
			1. No body but this request requires an organization id and user id.
			2. This request removes a specific user from a specific organization but deletes neither from the database.
