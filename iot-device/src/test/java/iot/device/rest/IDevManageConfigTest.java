package iot.device.rest;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;

import common.data.ConfigurationModification;
import common.rest.RESOURCE_NAMING;
import iot.device.ApplicationTestContext;
import iot.device.repo.DeliveryTaskRO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTestContext.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class IDevManageConfigTest extends AbstractRestTest {

    private ConfigurationModification cm;

    /**
     * Implicitly test method setConfiguration
     * 
     */
    @Before
    public void before() throws IOException {

        super.before();

        this.deliveryStart();

        Properties properties = new Properties();
        properties.put("key", "value");

        cm = new ConfigurationModification();
        cm.setDataSink(ep);
        cm.setProperties(properties);

        ResponseBodyExtractionOptions response = given().body(cm).contentType(ContentType.JSON).post(RESOURCE_NAMING.IDEV_SET_CONFIGURATION.getPath())
                //
                .then().contentType(ContentType.TEXT)
                //
                .extract().body();

        response.asString();

        // String result = response.getBody().as(String.class, ObjectMapper.JAXB);

        assertNotNull(response);
        assertEquals("OK", response.asString());

    }

    @After
    public void after() {
        this.deliveryStop();
    }

    @Test
    public void getAllConfiguration() {

        Response response = get(RESOURCE_NAMING.IDEV_GET_ALL_CONFIGURATION.getPath());

        List<DeliveryTaskRO> result = Arrays.asList(response.getBody().as(DeliveryTaskRO[].class));

        assertNotNull(result);
        assertTrue(result.size() >= 1);
        assertEquals(cm.getDataSink().getUrl(), result.get(0).getUrlDataSink());

    }

    @Test
    public void getConfigurationByEPAuthority() {

        String path = RESOURCE_NAMING.IDEV_GET_CONFIGURATION_BY_EP.getPath();
        path = path.replace("{authority}", cm.getDataSink().getUrl().getAuthority());

        Response response = get(path);

        DeliveryTaskRO result = response.getBody().as(DeliveryTaskRO.class);

        assertNotNull(result);
        assertEquals(cm.getDataSink().getUrl(), result.getUrlDataSink());

    }
}
