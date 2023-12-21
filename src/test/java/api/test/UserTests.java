package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

//Testcases for user model
public class UserTests {
	
	Faker faker;
	User userPayLoad;
	
	@BeforeClass	
	public void setupData()
	{
		faker = new Faker();
		userPayLoad = new User();
		
		
		//passing the faker data to the pojo data
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstname(faker.name().firstName());
		userPayLoad.setLastname(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password(5,10 ));
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());	
		
	}
	
	//Testcase for User module endpoints
	
	@Test(priority=1)
	public void testPostUser()
	{	
		Response response = UserEndPoints.createUser(userPayLoad);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		Response response = UserEndPoints.readUser(this.userPayLoad.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		userPayLoad.setFirstname(faker.name().firstName());
		userPayLoad.setLastname(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(this.userPayLoad.getUsername(), userPayLoad);
		response.then().log().body();
		
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Checking data after update
		
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayLoad.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		Response response = UserEndPoints.deleteUser(this.userPayLoad.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	
	

}
