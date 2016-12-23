package StepDef;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.testng.Assert;
import org.testng.internal.Yaml;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.OAuthSignature;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class SmokeTest {
	
	Response r, r1;
	String responsebody;
	static String jwttoken;
	
	
	ResourceBundle rb=ResourceBundle.getBundle("DataManage");

	@Given("^I have POST method with request createIamToken and input Body \"(.*?)\" and \"(.*?)\"$")
	public void i_have_POST_method_with_request_createIamToken_and_input_Body_and(String Pass, String User) throws Throwable {
		

		RestAssured.baseURI = "https://stage-api.core.merrillcorp.com";
		r = RestAssured.given().contentType("application/json")
				.body("{\"password\": \"" + Pass + "\", \"username\": \"" + User + "\"}").when().post("/api/tokens/login");
		
	}

	@Then("^Validate Status code$")
	public void validate_Status_code() throws Throwable {
		
		int staticcode = r.getStatusCode();
		System.out.println(staticcode);
	    
	}

	@Then("^Validate Response Body$")
	public void validate_Response_Body() throws Throwable {
		
		responsebody = r.getBody().asString();
		System.out.println(responsebody);
	    
	}

	@Then("^Save JWT token in a variable$")
	public void save_JWT_token_in_a_variable() throws Throwable {
		JsonPath jsonPath = new JsonPath(responsebody);
		jwttoken = jsonPath.get("jwt");
		System.out.println("Here is JWT Token: " + jwttoken);
	    
	}
	
	
	@Given("^I have POST method with request createProject and JWT token with input body parameters mentioned in Yml file$")
	public void i_have_POST_method_with_request_createProject_and_JWT_token_with_input_body_parameters_mentioned_in_Yml_file() throws Throwable {
		
		System.out.println(jwttoken);
		r1 = RestAssured.given().authentication().oauth2(jwttoken, OAuthSignature.HEADER)
				.contentType("application/json")
				.body("{\r\n\"projectInfo\": {\r\n  \"active\": true,\r\n  \"datacenter\": \"Unique_DCenter_3\",\r\n  \"emailId\": \""+rb.getString("emailId")+"\",\r\n  \"projectDescription\": \""+rb.getString("projectDescription")+"\",\r\n  \"projectName\": \""+rb.getString("projectName")+"\",\r\n  \"projectType\": \""+rb.getString("projectType")+"\",\r\n  \"salesforceProjectId\": \""+rb.getString("salesforceProjectId")+"\"\r\n}\r\n\r\n}")
				.when().post("/api/projects");
	    
	}

	@Then("^Validate Status code for createProject$")
	public void validate_Status_code_for_createProject() throws Throwable {
		System.out.println(r1.getStatusCode());
	}

	@Then("^Validate Response Body for createProject$")
	public void validate_Response_Body_for_createProject() throws Throwable {
		String responsebody1=r1.getBody().asString();
		System.out.println(responsebody1);
		JsonPath jsonPath = new JsonPath(responsebody1);
		String projectId = jsonPath.get("projectId");
		System.out.println("Here is Your ProjectID is: " + projectId); 
	}
	

	/*Response r, r1;
	String responsebody;
	static String jwttoken;
	

	@Given("^Making api token request with post method API$")
	public void making_api_token_request_with_post_method_API(String token) throws Throwable {
		
	
		
		YamlReader reader = new YamlReader(new FileReader("Mapplication.yml"));

		Object object = reader.read();

		Map map = (Map) object;
		String pass = (String) map.get("password");

		String user = (String) map.get("username");

		String baseurl = (String) map.get("baseURL");

		String api = (String) map.get("Api");

		RestAssured.baseURI = baseurl;
		r = RestAssured.given().contentType("application/json")
				.body("{\"password\": \"" + pass + "\", \"username\": \"" + user + "\"}").when().post(api);

	}

	@Then("^Validate Status code$")
	public void validate_Status_code() throws Throwable {
        int staticcode = r.getStatusCode();
		System.out.println(staticcode);

	}

	@Then("^Validate Response Body$")
	public void validate_Response_Body() throws Throwable {
		responsebody = r.getBody().asString();
		System.out.println(responsebody);

	}

	@Then("^Save JWT token in a variable$")
	public void save_JWT_token_in_a_variable() throws Throwable {

		JsonPath jsonPath = new JsonPath(responsebody);
		jwttoken = jsonPath.get("jwt");
		System.out.println("Here is JWT Token: " + jwttoken);

	}

	@Given("^make project call with input body and post method api$")
	public void make_project_call_with_input_body_and_post_method_api() throws Throwable {
		Thread.sleep(5000);

		System.out.println(jwttoken);
		RestAssured.baseURI = "https://stage-api.core.merrillcorp.com";
		r1 = RestAssured.given().authentication().oauth2(jwttoken, OAuthSignature.HEADER)
				.contentType("application/json")
				.body("{\r\n\"projectInfo\": {\r\n  \"active\": true,\r\n  \"datacenter\": \"Unique_DCenter_3\",\r\n  \"emailId\": \"dummyiud101@gmail.com\",\r\n  \"projectDescription\": \"Mohit_sharma_project_details101\",\r\n  \"projectName\": \"Mohit_Pjroject101\",\r\n  \"projectType\": \"jev\",\r\n  \"salesforceProjectId\": \"SalesForjce_101\"\r\n}\r\n\r\n}")
				.when().post("/api/projects");

	}

	@Then("^Validate Status for project code$")
	public void validate_Status_for_project_code() throws Throwable {
		System.out.println(r1.getStatusCode());

	}

	@Then("^Validate Response Body for project$")
	public void validate_Response_Body_for_project() throws Throwable {
		String responsebody1=r1.getBody().asString();
		System.out.println(responsebody1);
		JsonPath jsonPath = new JsonPath(responsebody1);
		String projectId = jsonPath.get("projectId");
		System.out.println("Here is Your ProjectID is: " + projectId);
		
		

	}*/

}

