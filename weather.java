import org.testng.annotations.Test;
import java.util.Properties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;

public class weather {
	Properties prop=new Properties();
	
	@Test
    public void Banglr()throws Exception
    {
		FileInputStream fls= new FileInputStream("C:\\New folder\\Apiautomation\\data.properties");
		prop.load(fls);
		String consumerKey = prop.getProperty("consumerKey");
		String consumerSecret = prop.getProperty("consumerSecret");
		String token = prop.getProperty("token");
		String tokenSecret = prop.getProperty("tokenSecret");
		
    RestAssured.baseURI = "https://api.twitter.com/1.1/search";
    Response res= given().auth().oauth(consumerKey,consumerSecret,token,tokenSecret).
    param("q","weather.Banglore").
    when().
    get("/tweets.json").
    then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
    extract().response();
   
    String response = res.asString();
    System.out.println(response);
    }
	

}
