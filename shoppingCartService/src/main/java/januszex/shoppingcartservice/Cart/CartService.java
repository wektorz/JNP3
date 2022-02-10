package januszex.shoppingcartservice.Cart;

import com.sun.source.tree.Tree;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(CartService.class);
    private final String authUrl = "http://login_service:8080/api/auth";
    private final String productUrl = "http:://products_service:8080/api/products";

    @Autowired
    public CartService(CartRepository cartRepository, RestTemplateBuilder restTemplateBuilder){
        this.cartRepository = cartRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    public ArrayList<ProductDTO> getCart(UserPair user) throws Exception {
        //isAuthed
//        if (restTemplate.postForEntity(authUrl, makeAuthPostBody(user), Object.class).getStatusCode() != HttpStatus.OK)
//            throw new Exception("400");

        Cart cartToGet = cartRepository.findById(user.getLogin()).orElseThrow();
        return new ArrayList<>(cartToGet.getCartProducts().values());
    }

    public void addToCart(CartDTO request) throws Exception {
        //isAuthed
//        if (restTemplate.postForEntity(authUrl, makeAuthPostBody(new UserPair(request.getCookie(), request.getLogin())), Object.class).getStatusCode() != HttpStatus.OK)
//            throw new Exception("400");

        //if no cart present build new one
        Cart cartToUpdate = cartRepository.findById(request.getLogin()).orElse(new Cart(request.getLogin(), new TreeMap<>()));
        if(cartToUpdate.getCartProducts() == null)
            cartToUpdate.setCartProducts(new TreeMap<>());
        logger.error(cartToUpdate.getCartProducts().toString());
        //get product json
//        HttpEntity<String> response = restTemplate.getForEntity(productUrl + String.format("?itemId=%d&noImg=true", request.getItemId()), String.class);
//        cartToUpdate.getCartProducts().add(makeCartJson(response));
        ProductDTO product = new ProductDTO(request.getItemId(), "AMOGUS", 33, "siusiak", 1);
        cartToUpdate.getCartProducts().computeIfPresent(request.getItemId(),
                (key, value) -> new ProductDTO(value.getId(), value.getDesc(), value.getPrice(), value.getImg(), value.getQuantity() + product.getQuantity()));
        cartToUpdate.getCartProducts().putIfAbsent(request.getItemId(), product);
        cartRepository.save(cartToUpdate);
    }

    public void deleteFromCart(CartDTO request) throws Exception {
        //isAuthed
//        if (restTemplate.postForEntity(authUrl, makeAuthPostBody(new UserPair(request.getCookie(), request.getLogin())), Object.class).getStatusCode() != HttpStatus.OK)
//            throw new Exception("400");

        Cart cartToUpdate = cartRepository.findById(request.getLogin()).orElseThrow();
        if (cartToUpdate.getCartProducts().get(request.getItemId()).getQuantity() <= request.getQuantity())
            cartToUpdate.getCartProducts().remove(request.getItemId());
        else
            cartToUpdate.getCartProducts().computeIfPresent(request.getItemId(),
                    (key, value) -> new ProductDTO(value.getId(), value.getDesc(), value.getPrice(), value.getImg(), value.getQuantity() - request.getQuantity()));
        //get product json
        //cartToUpdate.getCartProducts().add(product);
        cartRepository.save(cartToUpdate);
    }

    public void buy(BuyDTO comparisonCart) throws Exception {
        //isAuthed
        if (restTemplate.postForEntity(authUrl, makeAuthPostBody(new UserPair(comparisonCart.getCookie(), comparisonCart.getLogin())),
                Object.class).getStatusCode() != HttpStatus.OK)
            throw new Exception("400");

        if (!compareCarts(comparisonCart.getCart()))
            throw new Exception("400");
        cartRepository.deleteById(comparisonCart.getLogin());
    }


    private HttpEntity<Map<String, Object>> makeAuthPostBody(UserPair user){
        // create headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Map<String, Object> entries = new HashMap<>();
        entries.put("login", user.getLogin());
        entries.put("cookie", user.getCookie());
        return new HttpEntity<>(entries);
    }

    private String makeCartJson(HttpEntity<String> response){
        return "";
    }

    private boolean compareCarts(ArrayList<ProductDTO> cart) {
        return true;
    }

}
