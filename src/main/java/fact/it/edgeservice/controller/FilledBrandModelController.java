package fact.it.edgeservice.controller;

import fact.it.edgeservice.model.Brand;
import fact.it.edgeservice.model.FilledBrandModel;
import fact.it.edgeservice.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class FilledBrandModelController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${brandservice.baseurl}")
    private String brandServiceBaseUrl;

    @Value("${modelservice.baseurl}")
    private String modelServiceBaseUrl;

    // Get all the car brands with their models
    @GetMapping("/cars")
    public List<FilledBrandModel> getAll() {
        List<FilledBrandModel> returnList = new ArrayList<>();

        ResponseEntity<List<Brand>> responseEntityBrands = restTemplate.exchange(
                "http://" + brandServiceBaseUrl + "/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Brand>>() {
                }
        );
        List<Brand> brands = responseEntityBrands.getBody();

        ResponseEntity<List<Model>> responseEntityModels = restTemplate.exchange(
                "http://" + modelServiceBaseUrl + "/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Model>>() {
                }
        );
        List<Model> models = responseEntityModels.getBody();

        for (Brand brand : brands) {
            List<Model> correspondingModels = responseEntityModels.getBody();
            for (Model model : models) {
                if (brand.getId() == model.getBrandId()) {
                    correspondingModels.add(model);
                }
            }
            returnList.add(new FilledBrandModel(brand, correspondingModels));
        }

        return returnList;
    }

    // Get all the car brands with their models
    @GetMapping("/cars/{country}")
    public List<FilledBrandModel> getBrandsByCountry(@PathVariable String country) {
        List<FilledBrandModel> returnList = new ArrayList<>();

        ResponseEntity<List<Brand>> responseEntityBrands = restTemplate.exchange(
                "http://" + brandServiceBaseUrl + "/brands/{country}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Brand>>() {
                }, country
        );
        List<Brand> brands = responseEntityBrands.getBody();

        ResponseEntity<List<Model>> responseEntityModels = restTemplate.exchange(
                "http://" + modelServiceBaseUrl + "/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Model>>() {
                }
        );
        List<Model> models = responseEntityModels.getBody();

        for (Brand brand : brands) {
            List<Model> correspondingModels = responseEntityModels.getBody();
            for (Model model : models) {
                if (brand.getId() == model.getBrandId()) {
                    correspondingModels.add(model);
                }
            }
            returnList.add(new FilledBrandModel(brand, correspondingModels));
        }

        return returnList;
    }

    // Get all the car brands
    @GetMapping("/brands")
    public List<Brand> getBrands() {
        List<Brand> returnList = new ArrayList<>();

        ResponseEntity<List<Brand>> responseEntityBrands = restTemplate.exchange(
                "http://" + brandServiceBaseUrl + "/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Brand>>() {
                }
        );
        returnList = responseEntityBrands.getBody();

        return returnList;
    }

    // Get all the car models
    @GetMapping("/models")
    public List<Model> getModels() {
        List<Model> returnList = new ArrayList<>();

        ResponseEntity<List<Model>> responseEntityModels = restTemplate.exchange(
                "http://" + modelServiceBaseUrl + "/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Model>>() {
                }
        );
        returnList = responseEntityModels.getBody();

        return returnList;
    }
}
