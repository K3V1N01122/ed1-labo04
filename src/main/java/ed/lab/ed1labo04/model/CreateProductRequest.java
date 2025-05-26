package ed.lab.ed1labo04.model;

public class CreateProductRequest {

    private String name;
    private Double price;

    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    @SuppressWarnings("unused")
    public void setPrice(Double price) {
        this.price = price;
    }
}


