package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userId, String userName, String fname, String lname, String useremail, String pwd, String ph)
	{
		User userPayLoad = new User();
		userPayLoad.setId(Integer.parseInt(userId));
		userPayLoad.setUsername(userName);
		userPayLoad.setFirstname(fname);
		userPayLoad.setLastname(lname);
		userPayLoad.setEmail(useremail);
		userPayLoad.setPassword(pwd);
		userPayLoad.setPhone(ph);	
		
		Response response = UserEndPoints.createUser(userPayLoad);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
