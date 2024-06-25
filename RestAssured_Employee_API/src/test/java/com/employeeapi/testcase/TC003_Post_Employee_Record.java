package com.employeeapi.testcase;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_Post_Employee_Record extends TestBase {

	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	void createEmployee() throws InterruptedException {
		logger.info("************** Started TC003_Post_Employee_Record **************");
		// Getting Data from propertyfile
		String baseURI = prop.getProperty("baseURI");
		// Specify base URI
		RestAssured.baseURI = baseURI;
		// Request Object
		httpRequest = RestAssured.given();
		// Request Payload Sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		// Getting Data from propertyfile
		String contentType = prop.getProperty("contentType");
		// Add a header Stating the Request body is a json
		httpRequest.header("Content-Type", contentType);
		// Puting body payload into httpRequest
		httpRequest.body(requestParams.toJSONString());
		// Getting data from propertiesfiles
		String createEmployeesEndPoint = prop.getProperty("createEmployees");
		// Response Object
		response = httpRequest.request(Method.POST, createEmployeesEndPoint);
		Thread.sleep(5000);
	}
	
	@Test
	void checkResposeBody() {
		logger.info("************** Checking Response Body **************");
		//Get only response body from whole respons
		String responseBody=response.getBody().asString();
		//Print The response in the console and logger
		logger.info("Response Body==>"+responseBody);
		Assert.assertEquals(responseBody.contains(empName),true);
      Assert.assertEquals(responseBody.contains(empAge),true);
      Assert.assertEquals(responseBody.contains(empSalary),true);
	 }
	
	@Test()
	void checkStatusCode() {
		logger.info("************** Checking Status Code **************");
		//Getting Status Code
		int statusCode=response.getStatusCode();
		//Print The status code in the console and logger
		logger.info("Status Code Is==>"+statusCode);
		// Getting data from propertiesfile
		String StatusCode_200= prop.getProperty("responseStatusCode_200");
		int responseStatusCode_200=Integer.parseInt(StatusCode_200);
		Assert.assertEquals(statusCode, responseStatusCode_200);
	}
	
	@Test
	void checkResponseTime() {
		logger.info("************** Checking Response Time **************");
		//Getting Status Time
		long responseTime=response.getTime();
		//Print The Response Time in the console and logger
		logger.info("Response Time is==>"+responseTime);
		// Getting data from propertiesfile
		String expectedResponseTime=prop.getProperty("responseTime");
		long expectedResponseTimeInLong=Long.parseLong(expectedResponseTime);
		if(responseTime>7000) 
		logger.warn("Response Time is Gratter than 7000");
	    Assert.assertTrue(responseTime<=expectedResponseTimeInLong);
	}
	
	@Test
	void checkStatusLine() {
		logger.info("************** Checking Status Line **************");
		//Getting Status Line
		String statusLine=response.statusLine();
		//Print The Status Line in the console and logger
		logger.info("Status Line is==>"+statusLine);
		String statusLine_200=prop.getProperty("statusLine_200");
		Assert.assertEquals(statusLine, statusLine_200);
	}
	
	@Test
	void checkContentType() {
		logger.info("************** Checking Content Type **************");
		//Verifying Header Response
		String contentType=response.header("Content-Type");
		//Print The Content Type in the console and logger
		logger.info("Content-Type is==>"+contentType);
		// Getting data from propertiesfile
		String expectedContentType=prop.getProperty("contentType");
		Assert.assertEquals(contentType, expectedContentType);
	}
	
	@Test
	void checkServerType() {
		logger.info("************** Checking Server Type **************");
		//Verifying Header Response
		String serverType=response.header("Server");
		//Print The Content Type in the console and logger
		logger.info("Server-Type is==>"+serverType);
		// Getting data from propertiesfile
		String expectedServerType=prop.getProperty("createServerType");
		Assert.assertEquals(serverType, expectedServerType);
	}
	
	@Test
	void checkContentEncoding() {
		logger.info("************** Checking Content Encoding **************");
		//Verifying Header Response
		String contentEncoding=response.header("Content-Encoding");
		//Print The Content Type in the console and logger
		logger.info("Content-Encoding is==>"+contentEncoding);
		// Getting data from propertiesfile
		String expectedContentEncoding=prop.getProperty("contentEncoding");
		Assert.assertEquals(contentEncoding, expectedContentEncoding);
	}
	
	@AfterClass
	void tearDown() {
		logger.info("************** Finished TC003_Post_Employee_Record **************");
	}
}
