package ed.lab.ed1labo04.model;

import java.util.List;

public record CartItemRequest(List<Item> cartItems) {
    public record Item(long productId, int quantity) {}
}
