package ed.lab.ed1labo04.controller; //Kevin Palencia

import ed.lab.ed1labo04.model.CartItemRequest;
import ed.lab.ed1labo04.model.CartResponseDTO;
import ed.lab.ed1labo04.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartResponseDTO> createCart(@RequestBody CartItemRequest request) {
        try {
            CartResponseDTO response = cartService.createCart(request);
            return ResponseEntity.status(201).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDTO> getCart(@PathVariable Long id) {
        try {
            CartResponseDTO response = cartService.getCartById(id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
