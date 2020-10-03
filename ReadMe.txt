This is a REST API for a simple real estate registry for buildings. 
MAIN: the main class called DemoApplication, from this point we can run the application. 
CRUD endpoints to retrieve, save, and change existing building records:
	PUT:
		1.http://localhost:8080/api/buildings/{buildingId}
		This endpoint uses a put request to update specific building. It requires requestBody in Json format. 
		Example: {
				"id": 0, 
				"address": "string",
				"owner": "string",
				"size": 0, 
				"value": 0, 
				"type": "string",
				"tax": 0
				}
		Size, value, and tax should be provided as double. The tax value should be 0.0 unless it was changed with another put request.
		Also buildingId,which is provided in link have to match with id in requestBody. This request works with curl command or in postman or other program where you can input request body. Example with curl command when we want to
		modify building, which id is 3:
		curl -X PUT "http://localhost:8080/api/buildings/3" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"id\":3,\"address\":\"Vilnius Naujoji gatve 24\",\"owner\":\"Kazimieras Petrauskas\",\"size\":252.25,\"value\":452010.25,\"type\":\"house\",\"tax\":0.0}"
		In this command you can specify all parameters that you would like to change (update).
		2. http://localhost:8080/api/buildings/taxes/{buildingId}
		This endpoint uses a put request to update specific building tax parameter. It calcalute taxes by formula, which is taxes = value of building * tax rate. In this registry system 
		tax rate is from 0.5% to 2% or from 0.005 to 0.2 depending on building value. Example: if we want to update building tax, which id is 2, we would write:
		curl -X PUT "http://localhost:8080/api/buildings/taxes/2" -H "accept: application/json" in the command line.
		When building tax is updated, the message will appear: Building, which ID is: 2 tax parameter successfully updated.
	GET:
		1. http://localhost:8080/api/buildings
		This endpoint uses a get request to retrieve all buildings from H2 database.
		2. http://localhost:8080/api/buildings/{buildingId}
		This endpoint uses a get request to retrieve specific building from H2 database. Information will be shown in Json format. Example: if we would like
		to see details about first building: hhtp://localhost:8080/api/buildings/1 
		3. http://localhost:8080/api/buildings/owner
		This endpoint uses a get request to calculate the total taxes of the specified owner. Information will be shown in the String format. Example: if we would like 
		to see total taxes of Kazimieras Petrauskas, we would write: http://localhost:8080/api/buildings/owner?owner=Kazimieras%20Petrauskas
	POST:
		1. http://localhost:8080/api/buildings
		This endpoint uses a post request to create and upload a new building object to the H2 database. Information will be stored in the table. Example: if we would like
		to upload a new building to the system, we would write: http://localhost:8080/api/buildings?address=Vilnius%20Naujoji%20street%2042&owner=Kazimieras%20Petrauskas&size=142.25&value=254200.24&type=house .
		As not all webs support POST request, we can use console command: curl -X POST "http://localhost:8080/api/buildings?address=Vilnius%20Naujoji%20street%2042&owner=Kazimieras%20Petrauskas&size=142.25&value=254200.24&type=house" -H "accept: application/json" -d ""
		Other parameters: id and tax, they have default values, tax = 0.0, and Id always increase by one. All parameters each time can be specified differently. When the new building is uploaded to the database, a message will appear:
		"Building Saved Successfully to H2 database, which can be found at http://localhost:8080/h2"
	DELETE:
		1. http://localhost:8080/api/buildings/{buildingsId}
		This endpoint uses a delete request to remove a specific building from the H2 database. Example: if we would like to delete building, which id is 2, we would write:
		http://localhost:8080/api/buildings/2 and again DELETE request is not always working on the web, so we can use console command:
		curl -X DELETE "http://localhost:8080/api/buildings/5" -H "accept: application/json"
		All changes can be seen in the H2 database. When the building is deleted from the database, a message will appear: 
		"Building Deleted Successfully from H2 database, which can be found at http://localhost:8080/h2"
	DATABASE:
		1.http://localhost:8080/h2
		All data, would it be uploaded, deleted or updated is stored in-memory H2 database. Connection parameters: DriverClassName=org.h2.Driver; username=sa;
		password=; url=jdbc:h2:mem:test;
SWAGGER DOCUMENTATION:
File called JavaTask_Swagger_Documentation
UNIT TESTS:
For unit tests, I used JUnit & Mockito. Tests can be found in the BuildingServiceTest class, there are 7 different tests.