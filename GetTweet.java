
	import org.testng.annotations.Test;
	import io.restassured.RestAssured;
	import io.restassured.response.Response;
	//import mainPackage.Resources1;

	import static io.restassured.RestAssured.given;

	import java.io.FileInputStream;
	import java.util.Properties;

	import io.restassured.path.json.JsonPath;

	public class GetTweet {
		
		Properties prop= new Properties();
		@Test
		public void getTweet() throws Exception {
			FileInputStream fls= new FileInputStream("C:\\New folder\\Apiautomation\\data.properties");
			prop.load(fls);
			
			String consumerKey = prop.getProperty("consumerKey");
			String consumerSecret = prop.getProperty("consumerSecret");
			String token = prop.getProperty("token");
			String tokenSecret = prop.getProperty("tokenSecret");
			
			
			RestAssured.baseURI= prop.getProperty("host");
			Response res= given().auth().oauth(consumerKey, consumerSecret, token, tokenSecret).
					when().get(Resources1.getResource1()).
					then().extract().response();
			String response= res.asString();
			System.out.println(response);
			JsonPath js= new JsonPath(response);
			String text= js.get("text").toString();
			System.out.println(text);
		}

	}



