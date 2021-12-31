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

    // Get all brands with all models
    @GetMapping("/cars")
    public List<FilledBrandModel> getAll() {
        List<FilledBrandModel> returnList = new ArrayList<>();

        List<Brand> brands = getBrands();
        List<Model> models = getModels();

        for (Brand brand : brands) {
            List<Model> correspondingModels = new ArrayList<>();
            for (Model model : models) {
                if (brand.getId().equals(model.getBrandId())) {
                    correspondingModels.add(model);
                }
            }
            returnList.add(new FilledBrandModel(brand, correspondingModels));
        }

        return returnList;
    }

    // Get all brands from specific country with all models
    @GetMapping("/cars/brands/country/{country}")
    public List<FilledBrandModel> getBrandsByCountry(@PathVariable String country) {
        List<FilledBrandModel> returnList = new ArrayList<>();

        ResponseEntity<List<Brand>> responseEntityBrands = restTemplate.exchange(
                "http://" + brandServiceBaseUrl + "/brands/country/{country}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Brand>>() {
                }, country
        );
        List<Brand> brands = responseEntityBrands.getBody();
        List<Model> models = getModels();

        for (Brand brand : brands) {
            List<Model> correspondingModels = new ArrayList<>();
            for (Model model : models) {
                if (brand.getId().equals(model.getBrandId())) {
                    correspondingModels.add(model);
                }
            }
            returnList.add(new FilledBrandModel(brand, correspondingModels));
        }

        return returnList;
    }

    // Get all the car brands
    private List<Brand> getBrands() {
        List<Brand> returnList = new ArrayList<>();

        ResponseEntity<List<Brand>> responseEntityBrands = restTemplate.exchange(
                "http://" + brandServiceBaseUrl + "/brands",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Brand>>() {
                }
        );
        returnList = responseEntityBrands.getBody();

        return returnList;
    }

    // Get all the car models
    private List<Model> getModels() {
        List<Model> returnList = new ArrayList<>();

        ResponseEntity<List<Model>> responseEntityModels = restTemplate.exchange(
                "http://" + modelServiceBaseUrl + "/models",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Model>>() {
                }
        );
        returnList = responseEntityModels.getBody();

        return returnList;
    }
}
