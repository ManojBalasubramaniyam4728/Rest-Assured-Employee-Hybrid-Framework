package com.employeeapi.base;

import java.io.FileInputStream; 
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public static String credentialFilePath = "C:\\Users\\user\\git\\repository6\\RestAssured_Employee_API\\src\\test\\java\\com\\employeeapi\\base\\credential.properties";
	// Hard coded Input for details of single employee and update employee
	public static String empID = "";
	// Logger
	public Logger logger;
	public Properties prop;

	@BeforeClass
	public void setUp() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(credentialFilePath);
		prop.load(fis);
		empID = prop.getProperty("employeeID");
		// add logger
		logger = Logger.getLogger("EmployesResAPI");
		// add logger
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}

}
