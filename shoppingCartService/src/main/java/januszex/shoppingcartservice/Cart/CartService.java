package januszex.shoppingcartservice.Cart;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public ArrayList<String> getCart(int id) {
        //isAuthed
        Cart cartToGet = cartRepository.findById(id).orElseThrow();
        return cartToGet.getCartProducts();
    }

    public void addToCart(CartDTO request) {
        //isAuthed
        Cart cartToUpdate = cartRepository.findById(request.getUserId()).orElse(new Cart(request.getUserId(), new ArrayList<>()));
        //get product json
        String product = String.format("{id: %d, desc: siusiu, price: 33, img: fork.png, quantity: %d}", request.getItemId(), request.getQuantity());
        cartToUpdate.getCartProducts().add(product);
        cartRepository.save(cartToUpdate);
    }

    public void deleteFromCart(CartDTO request) {
        //isAuthed
        Cart cartToUpdate = cartRepository.findById(request.getUserId()).orElseThrow();
        //get product json
        //cartToUpdate.getCartProducts().add(product);
        cartRepository.save(cartToUpdate);
    }

    public void buy(Cart comparisonCart) {
        //isAuthed

    }

}
