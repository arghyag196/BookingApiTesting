package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static String getJsonData(String response, String key){

        JsonPath js = new JsonPath(response);
        return js.getString(key);

    }

    public static  RequestSpecification requestSpec;
    public static RequestSpecification requestSpecification() throws IOException {

        if(requestSpec == null){
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpec = new RequestSpecBuilder().setBaseUri(Utils.getGlobalValue("baseUrl"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON)
                    .build();
        }
        return requestSpec;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream("D:/API_testing_demo/BookingApiTesting/src/test/java/resources/global.properties");
        properties.load(file);
        return properties.getProperty(key);
    }

}
