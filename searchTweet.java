import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class searchTweet {
	

	String ConsumerKey="2Xtd4ILUtinfACHehAvCNcbjW";
	String ConsumerSecret="pIHDmJy9fgKlLVuAgG4AuP5rRkaOy6TtWqxLqjVeJ2MywdcGJp";
	String Token="839104214-h1Kaf8iJW2FAyXzNCZNicI1a4Xb8BMw7ZkFKGzmJ";
	String TokenSecret="PTvXawkmrK3EZWeLSSmqtD7hisogRC3nqG48FYzNcLfQy";
	
@Test
public void search_tweet() {
	RestAssured.baseURI="https://api.twitter.com/1.1/search/";
	Response res=given().auth().oauth(ConsumerKey,ConsumerSecret,Token,TokenSecret).
	queryParam("q","#Qualitest")
	.when().get("/tweets.json").then().extract().response();
	String response=res.asString();
	System.out.println(response);
	
	
}

}
