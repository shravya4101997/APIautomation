


	import org.testng.annotations.Test;
	import io.restassured.RestAssured;
	import io.restassured.response.Response;
	//import mainPackage.Resources1;

	import static io.restassured.RestAssured.given;

	import java.io.FileInputStream;
	import java.util.Properties;

	import io.restassured.path.json.JsonPath;

	public class PostTweet {
		
		Properties prop= new Properties();
		
		@Test
		public void postTweet() throws Exception {
			FileInputStream fls= new FileInputStream("C:\\New folder\\Apiautomation\\data.properties");
			prop.load(fls);
			
			String consumerKey = prop.getProperty("consumerKey");
			String consumerSecret = prop.getProperty("consumerSecret");
			String token = prop.getProperty("token");
			String tokenSecret = prop.getProperty("tokenSecret");
			
			RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
			Response res= given().auth().oauth(consumerKey, consumerSecret, token, tokenSecret).
					queryParam("status", prop.getProperty("tweet_value")).
					when().post(Resources1.postResource1()).
					then().extract().response();
			String response= res.asString();
			System.out.println(response);
			JsonPath js= new JsonPath(response);
			
			String text= js.get("text").toString();
			System.out.println(text);
			
			String id= js.get("id").toString();
			System.out.println(id);
			
			//delete the tweet
			given().auth().oauth(consumerKey, consumerSecret, token, tokenSecret).
			when().post("/destroy/"+id+".json").
			then().assertThat().statusCode(200);
		}

	}



