package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.DeveloperDto;
import entities.Developer;
import entities.Project;
import entities.ProjectHour;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static rest.LoginEndpointTest.startServer;

public class DeveloperEndpointTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();

    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    Project p1, p2;

    Developer d1, d2;

    ProjectHour ph1, ph2;

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        p1 = new Project("project 1", "museum app");
        d1 = new Developer("Karen", "karen@karen.dk", "28283928", 200);
        ph1 = new ProjectHour(22, "asdas", 1, d1, p1);

        try{
            em.getTransaction().begin();
            em.createNamedQuery("ProjectHour.deleteAllRows").executeUpdate();
            em.createNamedQuery("Developer.deleteAllRows").executeUpdate();
            em.createNamedQuery("Project.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(d1);
            em.persist(ph1);
            em.getTransaction().commit();

        }finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/developer").then().statusCode(200);
    }

    @Test
    public void testLogRequest() {
        System.out.println("Testing logging request details");
        given().log().all()
                .when().get("/developer")
                .then().statusCode(200);
    }
    @Test
    public void testLogResponse() {
        System.out.println("Testing logging response details");
        given()
                .when().get("/developer")
                .then().log().body().statusCode(200);
    }

    @Disabled
    @Test
    public void getAll() {
        List<DeveloperDto> developerDtos;

        developerDtos = given()
                .contentType("application/json")
                .when()
                .get("/developer")
                .then()
                .extract().body().jsonPath().getList("", DeveloperDto.class);

        DeveloperDto d1Dto = new DeveloperDto(d1);
        assertThat(developerDtos, containsInAnyOrder(d1Dto)) ;
    }
}
