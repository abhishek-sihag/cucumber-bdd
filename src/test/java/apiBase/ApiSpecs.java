package apiBase;

import com.google.gson.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.PropertyUtil;

public class ApiSpecs {

    static final String BASE_URI = PropertyUtil.getInstance().get("base.uri");
    static final String BASE_GET_PATH = PropertyUtil.getInstance().get("base.path.get");
    static final String BASE_URI2 = PropertyUtil.getInstance().get("base.uri2");
    static final String BASE_GET2_PATH = PropertyUtil.getInstance().get("base.path2.get");
    static final String BASE_POST_PATH = PropertyUtil.getInstance().get("base.path.post");

    static final String EXP_RES_CODE_GET = PropertyUtil.getInstance().get("code.get");
    static final String EXP_RES_CODE_POST = PropertyUtil.getInstance().get("code.post");
    static final String EXP_RES_CODE_DELETE = PropertyUtil.getInstance().get("code.delete");

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(Integer.parseInt(EXP_RES_CODE_GET))
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification get2ResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(Integer.parseInt(EXP_RES_CODE_GET))
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification postResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(Integer.parseInt(EXP_RES_CODE_POST))
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification postResponseProd() {
        return new ResponseSpecBuilder()
                .expectStatusCode(Integer.parseInt(EXP_RES_CODE_POST))
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseDELETE(){
        return new ResponseSpecBuilder()
                .expectStatusCode(Integer.parseInt(EXP_RES_CODE_DELETE))
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_GET_PATH)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification get2RequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI2)
                .setBasePath(BASE_GET2_PATH)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification postRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_POST_PATH)
                .setBody(jsonObject())
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification postRequestProducts() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_POST_PATH)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static JsonObject jsonObject(){
        JsonObject json = new JsonObject();
        json.addProperty("id", 0);
        json.addProperty("title", "Ram");
        json.addProperty("price",0.1);
        json.addProperty("description","Not valid entry");
        json.addProperty("category","Kendra");
        json.addProperty("image","https://imgfff");
        return json;
    }
}
