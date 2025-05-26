package ed.lab.ed1labo04.model; //Kevin Palencia

public class UpdateProductRequest {

    private Double price;
    private int quantity;

    public Double getPrice() {
        return price;
    }

    @SuppressWarnings("unused")
    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    @SuppressWarnings("unused")
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

