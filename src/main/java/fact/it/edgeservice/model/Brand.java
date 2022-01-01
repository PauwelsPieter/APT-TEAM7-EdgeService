package fact.it.edgeservice.model;

public class Brand {
    private String id;
    private String name;
    private String country;
    private String foundingYear;

    public Brand() {
    }

    public Brand(String name, String country, String foundingYear) {
        this.name = name;
        this.country = country;
        this.foundingYear = foundingYear;
    }

    public Brand(String id, String name, String country, String foundingYear) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.foundingYear = foundingYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
