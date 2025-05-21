package ed.lab.ed1labo04.service;

import ed.lab.ed1labo04.entity.CartEntity;
import ed.lab.ed1labo04.entity.CartItemEntity;
import ed.lab.ed1labo04.entity.ProductEntity;
import ed.lab.ed1labo04.model.CartItemRequest;
import ed.lab.ed1labo04.model.CreateCartRequest;
import ed.lab.ed1labo04.repository.CartRepository;
import ed.lab.ed1labo04.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public CartEntity createCart(CreateCartRequest request) {
        List<CartItemEntity> items = new ArrayList<>();

        for (CartItemRequest itemRequest : request.getCartItems()) {
            if (itemRequest.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be positive");
            }

            Optional<ProductEntity> optionalProduct = productRepository.findById(itemRequest.getProductId());
            if (optionalProduct.isEmpty()) {
                throw new IllegalArgumentException("Product not found");
            }

            ProductEntity product = optionalProduct.get();
            if (product.getQuantity() < itemRequest.getQuantity()) {
                throw new IllegalArgumentException("Not enough product quantity");
            }

            // Restar la cantidad del producto
            product.setQuantity(product.getQuantity() - itemRequest.getQuantity());
            productRepository.save(product);

            CartItemEntity item = new CartItemEntity();
            item.setProductId(itemRequest.getProductId());
            item.setQuantity(itemRequest.getQuantity());
            items.add(item);
        }

        CartEntity cart = new CartEntity();
        cart.setCartItems(items);
        return cartRepository.save(cart);
    }

    public Optional<CartEntity> getCart(Long id) {
        return cartRepository.findById(id);
    }
}
