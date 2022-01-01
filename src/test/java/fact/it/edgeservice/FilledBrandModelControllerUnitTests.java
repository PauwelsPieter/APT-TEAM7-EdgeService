package fact.it.edgeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.edgeservice.model.Brand;
import fact.it.edgeservice.model.FilledBrandModel;
import fact.it.edgeservice.model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class FilledBrandModelControllerUnitTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${brandservice.baseurl}")
    private String brandServiceBaseUrl;

    @Value("${modelservice.baseurl}")
    private String modelServiceBaseUrl;

    private MockRestServiceServer mockServer;
    // ObjectMapper to transform objects to Json strings
    private ObjectMapper mapper = new ObjectMapper();

    private Brand brand1 = new Brand("1", "Honda", "Japan", "1948");
    private Brand brand2 = new Brand("2", "Tesla", "USA", "2003");
    private Brand brand3 = new Brand("3", "Dacia", "Romania", "1966");

    Model model1 = new Model(1, "1","2008","Sport", "221 hp", "Honda Civic Type R" );
    Model model2 = new Model(2, "1","1990","Sport", "270 hp", "Honda NSX I Coupe" );
    Model model3 = new Model(3, "1","2005","4x4", "160 hp", "Honda CR-V" );
    Model model4 = new Model(4, "2","2020","Electric", "417 hp", "Tesla Model X Long Range" );
    Model model5 = new Model(5, "2","2020","Electric", "346 hp", "Tesla Model 3 Long Range" );
    Model model6 = new Model(6, "3","2021","4x4", "90 hp", "Dacia Duster " );

    private List<Brand> allBrands = Arrays.asList(brand1, brand2, brand3);
    private List<Model> allModels = Arrays.asList(model1, model2, model3, model4, model5, model6);

    private FilledBrandModel filledBrandModel1 = new FilledBrandModel(brand1, Arrays.asList(model1, model2, model3));
    private FilledBrandModel filledBrandModel2 = new FilledBrandModel(brand2, Arrays.asList(model4, model5));
    private FilledBrandModel filledBrandModel3 = new FilledBrandModel(brand3, Arrays.asList(model6));

    private List<FilledBrandModel> allBrandsWithModels = Arrays.asList(filledBrandModel1, filledBrandModel2, filledBrandModel3);

    @BeforeEach
    public void initializeMockserver() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void whenGetAllBrands_thenReturnJsonBrands() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://" + brandServiceBaseUrl + "/brands")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(mapper.writeValueAsString(allBrands))
                );

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://" + modelServiceBaseUrl + "/models")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(allModels))
                );

        mockMvc.perform(get("/cars"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(brand1.getName())))
                .andExpect(jsonPath("$[0].country", is(brand1.getCountry())))
                .andExpect(jsonPath("$[0].foundingYear", is(brand1.getFoundingYear())))
                .andExpect(jsonPath("$[0].carModels[0].year", is(model1.getYear())))
                .andExpect(jsonPath("$[0].carModels[0].type", is(model1.getType())))
                .andExpect(jsonPath("$[0].carModels[0].engine", is(model1.getEngine())))
                .andExpect(jsonPath("$[0].carModels[0].name", is(model1.getName())))
                .andExpect(jsonPath("$[0].carModels[1].year", is(model2.getYear())))
                .andExpect(jsonPath("$[0].carModels[1].type", is(model2.getType())))
                .andExpect(jsonPath("$[0].carModels[1].engine", is(model2.getEngine())))
                .andExpect(jsonPath("$[0].carModels[1].name", is(model2.getName())))
                .andExpect(jsonPath("$[0].carModels[2].year", is(model3.getYear())))
                .andExpect(jsonPath("$[0].carModels[2].type", is(model3.getType())))
                .andExpect(jsonPath("$[0].carModels[2].engine", is(model3.getEngine())))
                .andExpect(jsonPath("$[0].carModels[2].name", is(model3.getName())))
                .andExpect(jsonPath("$[1].name", is(brand2.getName())))
                .andExpect(jsonPath("$[1].country", is(brand2.getCountry())))
                .andExpect(jsonPath("$[1].foundingYear", is(brand2.getFoundingYear())))
                .andExpect(jsonPath("$[1].carModels[0].year", is(model4.getYear())))
                .andExpect(jsonPath("$[1].carModels[0].type", is(model4.getType())))
                .andExpect(jsonPath("$[1].carModels[0].engine", is(model4.getEngine())))
                .andExpect(jsonPath("$[1].carModels[0].name", is(model4.getName())))
                .andExpect(jsonPath("$[1].carModels[1].year", is(model5.getYear())))
                .andExpect(jsonPath("$[1].carModels[1].type", is(model5.getType())))
                .andExpect(jsonPath("$[1].carModels[1].engine", is(model5.getEngine())))
                .andExpect(jsonPath("$[1].carModels[1].name", is(model5.getName())))
                .andExpect(jsonPath("$[2].name", is(brand3.getName())))
                .andExpect(jsonPath("$[2].country", is(brand3.getCountry())))
                .andExpect(jsonPath("$[2].foundingYear", is(brand3.getFoundingYear())))
                .andExpect(jsonPath("$[2].carModels[0].year", is(model6.getYear())))
                .andExpect(jsonPath("$[2].carModels[0].type", is(model6.getType())))
                .andExpect(jsonPath("$[2].carModels[0].engine", is(model6.getEngine())))
                .andExpect(jsonPath("$[2].carModels[0].name", is(model6.getName())));

    }
}
