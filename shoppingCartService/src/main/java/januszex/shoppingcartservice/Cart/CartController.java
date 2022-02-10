package januszex.shoppingcartservice.Cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/cart")
public class CartController {

    private final CartService cartService;
    private final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getCart(@RequestParam int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        try {
            return new ResponseEntity<>(cartService.getCart(id), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody CartDTO request){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        logger.error(request.toString());
        try {
            cartService.addToCart(request);
            return new ResponseEntity<>("Success", headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Failed", headers, 404);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFromCart(@RequestBody CartDTO request){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        try {

            cartService.deleteFromCart(request);
            return new ResponseEntity<>("Success", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", headers, 400);
        }
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyCart(@RequestBody Cart cart){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        try {
            cartService.buy(cart);
            return new ResponseEntity<>("Success", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", headers, 400);
        }
    }

}
