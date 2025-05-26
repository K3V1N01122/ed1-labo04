package ed.lab.ed1labo04.entity; //Kevin Palencia

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Double price;
    private int quantity;

    public ProductEntity() {}

    @SuppressWarnings("unused")
    public Long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
