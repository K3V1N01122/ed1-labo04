package ed.lab.ed1labo04.model;

import java.util.List;

public class CartResponseDTO {
    private Long id;
    private List<Item> cartItems;
    private double totalPrice;

    public CartResponseDTO(Long id, List<Item> cartItems, double totalPrice) {
        this.id = id;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public List<Item> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public static class Item {
        private long productId;
        private String name;
        private double price;
        private int quantity;

        public Item(long productId, String name, double price, int quantity) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public long getProductId() {
            return productId;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
