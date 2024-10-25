package coid.bcafinance.pcmtugasakhir.controller;

import coid.bcafinance.pcmtugasakhir.model.User;
import coid.bcafinance.pcmtugasakhir.repo.UserRepo;
import coid.bcafinance.pcmtugasakhir.utils.DataGenerator;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.Optional;
import java.util.Random;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserRepo userRepo;

    private Random rand;
    private DataGenerator dataGenerator;
    private JSONObject req;
    private User user;

    @BeforeClass
    private void setUp(){
        RestAssured.baseURI = "http://localhost:8080";
        rand = new Random();
        dataGenerator = new DataGenerator();
        req = new JSONObject();
        Optional<User> optionalUser= userRepo.findTopByOrderByIdDesc();
        user = optionalUser.get();
    }

    @Test(priority = 3)
    private void save(){
        req.put("username",dataGenerator.dataUsername());
        req.put("email",dataGenerator.dataEmail());
        req.put("no-hp",dataGenerator.dataNoHp());
        req.put("password",dataGenerator.dataPassword());
        req.put("tanggal-lahir",dataGenerator.dataTanggalLahir());
        req.put("alamat",dataGenerator.dataAlamat());
        req.put("nama-lengkap",dataGenerator.dataNamaLengkap());

        RequestSpecification httpRequest = given().
                header("Content-Type","application/json").
                header("Accept","*/*").
                body(req);

        String pathVariable = "/user";
        Response response = httpRequest.request(Method.POST, pathVariable);
        JsonPath jPath = response.jsonPath();
        ResponseBody responseBody = response.getBody();// seluruh body dari response
        System.out.println("====================================START RESPONSE BODY =================================================");
        System.out.println(responseBody.asPrettyString());// untuk melihat isi dari response body dalam bentuk JSON
    }

    @Test(priority = 7)
    private void update(){
        req.put("username",dataGenerator.dataUsername());
        req.put("email",dataGenerator.dataEmail());
        req.put("no-hp",dataGenerator.dataNoHp());
        req.put("password",dataGenerator.dataPassword());
        req.put("tanggal-lahir",dataGenerator.dataTanggalLahir());
        req.put("alamat",dataGenerator.dataAlamat());
        req.put("nama-lengkap",dataGenerator.dataNamaLengkap());
        RequestSpecification httpRequest = given().
                header("Content-Type","application/json").
                header("Accept","*/*").
                body(req);

        String pathVariable = "/user/"+user.getId();
        Response response = httpRequest.request(Method.PUT, pathVariable);
        JsonPath jPath = response.jsonPath();
        ResponseBody responseBody = response.getBody();// seluruh body dari response
        System.out.println("====================================START RESPONSE BODY =================================================");
        System.out.println(responseBody.asPrettyString());// untuk melihat isi dari response body dalam bentuk JSON
    }
}