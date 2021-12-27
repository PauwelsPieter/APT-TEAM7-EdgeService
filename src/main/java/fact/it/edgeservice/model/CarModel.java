package fact.it.edgeservice.model;

public class CarModel {
    private String year;
    private String type;
    private String engine;
    private String name;

    public CarModel(String year, String type, String engine, String name) {
        this.year = year;
        this.type = type;
        this.engine = engine;
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
