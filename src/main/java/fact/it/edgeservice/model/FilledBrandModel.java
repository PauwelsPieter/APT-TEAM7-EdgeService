package fact.it.edgeservice.model;

import java.util.ArrayList;
import java.util.List;

// Object returned to front-end
public class FilledBrandModel {
    private String id;
    private String name;
    private String country;
    private String foundingYear;
    private List<CarModel> carModels;

    public FilledBrandModel(Brand brand, List<Model> models) {
        setId(brand.getId());
        setName(brand.getName());
        setCountry(brand.getCountry());
        setFoundingYear(brand.getFoundingYear());
        carModels = new ArrayList<>();
        models.forEach(model -> {
            carModels.add(new CarModel(model.getYear(), model.getType(), model.getEngine(), model.getName()));
        });
        setCarModels(carModels);
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(String foundingYear) {
        this.foundingYear = foundingYear;
    }

    public List<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }
}
