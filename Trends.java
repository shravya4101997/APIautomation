
	import java.io.FileInputStream;
	import java.util.Properties;
	import static io.restassured.RestAssured.given;

	import org.testng.annotations.Test;

	import io.restassured.RestAssured;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;

	public class Trends {
		
		Properties prop = new Properties();
		
		@Test
		public void trendingHashtags() throws Exception {
			
			FileInputStream fls = new FileInputStream("C:\\New folder\\Apiautomation\\data.properties");
			prop.load(fls);
			
			String consumerKey = prop.getProperty("consumerKey");
			String consumerSecret = prop.getProperty("consumerSecret");
			String token = prop.getProperty("token");
			String tokenSecret = prop.getProperty("tokenSecret");
			
			String[] woeid= {"2295383", "28218", "23424977", "23424852"};
			
			for(int i=0; i<4; i++) {
			
			RestAssured.baseURI= prop.getProperty("trends_uri");
			Response res= given().auth().oauth(consumerKey, consumerSecret, token, tokenSecret).
			queryParam("id", woeid[i]).
			when().get("/trends/place.json").
			then().extract().response();
			
			String response= res.asString();
			System.out.println(response);
			
			/*JsonPath js= new JsonPath(response);
			String name= js.get("trends").toString();
			System.out.println(name);*/
		}

	}
	}

