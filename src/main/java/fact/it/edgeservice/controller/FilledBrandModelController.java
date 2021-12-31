package fact.it.edgeservice.controller;

import fact.it.edgeservice.model.Brand;
import fact.it.edgeservice.model.FilledBrandModel;
import fact.it.edgeservice.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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

    // Get all brands from specific year with all models
    @GetMapping("/cars/models/year/{year}")
    public List<FilledBrandModel> getModelsByYear(@PathVariable String year) {
        List<FilledBrandModel> returnList = new ArrayList<>();

        List<Brand> brands = getBrands();
        List<Model> models = getModels();

        // Select the models that are from the given year
        List<Model> correspondingModels = new ArrayList<>();
        for (Model model : models) {
            if (model.getYear().equals(year)) {
                correspondingModels.add(model);
            }
        }

        for (Brand brand : brands) {
            // Add models with the selected year that are from the current brand to an array
            List<Model> correspondingModels2 = new ArrayList<>();
            for (Model model : correspondingModels) {
                if (model.getBrandId().equals(brand.getId())) {
                    correspondingModels2.add(model);
                }
            }

            if (correspondingModels2.size() > 0) {
                returnList.add(new FilledBrandModel(brand, correspondingModels2));
            }
        }

        return returnList;
    }

    // Get all brands from specific type with all models
    @GetMapping("/cars/models/type/{type}")
    public List<FilledBrandModel> getModelsByType(@PathVariable String type) {
        List<FilledBrandModel> returnList = new ArrayList<>();

        List<Brand> brands = getBrands();
        List<Model> models = getModels();

        // Select the models that are from the given type
        List<Model> correspondingModels = new ArrayList<>();
        for (Model model : models) {
            if (model.getType().equals(type)) {
                correspondingModels.add(model);
            }
        }

        for (Brand brand : brands) {
            // Add models with the selected type that are from the current brand to an array
            List<Model> correspondingModels2 = new ArrayList<>();
            for (Model model : correspondingModels) {
                if (model.getBrandId().equals(brand.getId())) {
                    correspondingModels2.add(model);
                }
            }

            if (correspondingModels2.size() > 0) {
                returnList.add(new FilledBrandModel(brand, correspondingModels2));
            }
        }

        return returnList;
    }

    @PostMapping("/cars/brands")
    public Brand addBrand(@RequestBody Brand brand){
        Brand postResponse = restTemplate.postForObject("http://" + brandServiceBaseUrl + "/brands", brand, Brand.class);

        return postResponse;
    }

    @PutMapping("/cars/brands")
    public Brand updateBrand(@RequestBody Brand brand){
        ResponseEntity<Brand> responseEntityBrand = restTemplate.exchange("http://" + brandServiceBaseUrl + "/brands", HttpMethod.PUT, new HttpEntity<>(brand), Brand.class);

        Brand putResponse = responseEntityBrand.getBody();

        return putResponse;
    }

    @DeleteMapping("/cars/models/{id}")
    public ResponseEntity deleteModel(@PathVariable String id) {
        restTemplate.delete("http://" + modelServiceBaseUrl + "/models/" + id);

        return ResponseEntity.ok().build();
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
