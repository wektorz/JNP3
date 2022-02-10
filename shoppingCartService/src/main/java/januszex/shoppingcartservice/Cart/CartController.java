package januszex.shoppingcartservice.Cart;

import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
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
    public ResponseEntity<List<String>> getCart(@RequestParam String cookie, @RequestParam String login) {
        try {
            return new ResponseEntity<>(cartService.getCart(new UserPair(cookie, login)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody CartDTO request){
        logger.error(request.toString());
        try {
            cartService.addToCart(request);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFromCart(@RequestBody CartDTO request){
        try {
            cartService.deleteFromCart(request);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyCart(@RequestBody BuyDTO cart){
        try {
            cartService.buy(cart);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

}
