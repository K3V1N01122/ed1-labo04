package ed.lab.ed1labo04.service; //Kevin Palencia

import ed.lab.ed1labo04.entity.ProductEntity;
import ed.lab.ed1labo04.model.CartItemRequest;
import ed.lab.ed1labo04.model.CartResponseDTO;
import ed.lab.ed1labo04.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final Map<Long, CartResponseDTO> cartStorage = new HashMap<>();
    private long nextCartId = 1;

    @Autowired
    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public CartResponseDTO createCart(CartItemRequest request) {
        List<CartResponseDTO.Item> cartItems = new ArrayList<>();
        double totalPrice = 0;

        for (CartItemRequest.Item item : request.cartItems()) {
            if (item.quantity() <= 0 || item.quantity() > 1000) {
                throw new IllegalArgumentException("Cantidad inválida");
            }

            ProductEntity product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

            if (item.quantity() > product.getQuantity()) {
                throw new IllegalArgumentException("Cantidad supera el stock disponible");
            }

            product.setQuantity(product.getQuantity() - item.quantity());
            productRepository.save(product);

            double unitPrice = product.getPrice();
            totalPrice += unitPrice * item.quantity();

            cartItems.add(new CartResponseDTO.Item(
                    product.getId(),
                    product.getName(),
                    unitPrice,
                    item.quantity()
            ));
        }

        long cartId = nextCartId++;
        CartResponseDTO cart = new CartResponseDTO(cartId, cartItems, totalPrice);
        cartStorage.put(cartId, cart);
        return cart;
    }

    public CartResponseDTO getCartById(Long id) {
        if (!cartStorage.containsKey(id)) {
            throw new IllegalArgumentException("Carrito no encontrado");
        }
        return cartStorage.get(id);
    }
}
