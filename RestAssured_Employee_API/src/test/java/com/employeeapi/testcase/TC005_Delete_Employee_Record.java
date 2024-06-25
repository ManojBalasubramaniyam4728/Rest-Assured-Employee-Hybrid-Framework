package com.employeeapi.testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_Delete_Employee_Record extends TestBase {

	@BeforeClass
	void createEmployee() throws InterruptedException {
		logger.info("************** Started TC005_Delete_Employee_Record **************");
		// Getting Data from propertyfile
		String baseURI = prop.getProperty("baseURI");
		// Specify base URI
		RestAssured.baseURI = baseURI;
		// Request Object
		httpRequest = RestAssured.given();
		// Getting Data from propertyfile
		String getEmployeesEndPoint = prop.getProperty("getEmployees");
		response=httpRequest.request(Method.GET,getEmployeesEndPoint);
		//First get the jsonpath object instance from the response interface
		JsonPath jsonPathEvaluator=response.jsonPath();
		//Capture Id
		String empId=jsonPathEvaluator.getString("data[0].id");
		String deleteEmployeesEndPoint=prop.getProperty("deleteEmployees");
		response=httpRequest.request(Method.DELETE,deleteEmployeesEndPoint+empId);
		Thread.sleep(5000);
	}
	
	@Test
	void checkResposeBody() {
		logger.info("************** Checking Response Body **************");
		//Get only response body from whole respons
		String responseBody=response.getBody().asString();
		//Print The response in the console and logger
		logger.info("Response Body==>"+responseBody);
		// Getting data from propertiesfile
		String successfullMessage= prop.getProperty("SuccessfullMessage");
		Assert.assertEquals(responseBody.contains(successfullMessage), true);
	}
}
