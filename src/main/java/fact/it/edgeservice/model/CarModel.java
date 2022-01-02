package fact.it.edgeservice.model;

public class CarModel {
    private int id;
    private String year;
    private String type;
    private String engine;
    private String name;

    public CarModel(int id, String year, String type, String engine, String name) {
        this.id = id;
        this.year = year;
        this.type = type;
        this.engine = engine;
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public String getEngine() {
        return engine;
    }

    public String getName() {
        return name;
    }
}
