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

    public ArrayList<String> getCart(UserPair user) throws Exception {
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
        //get product json
//        HttpEntity<String> response = restTemplate.getForEntity(productUrl + String.format("?itemId=%d&noImg=true", request.getItemId()), String.class);
//        cartToUpdate.getCartProducts().add(response.getBody());
        String product = String.format("{id: %d, desc: siusiu, price: 33, img: fork.png, quantity: %d}", request.getItemId(), request.getQuantity());
        cartToUpdate.getCartProducts().put(request.getItemId(), product);

        cartRepository.save(cartToUpdate);
    }

    public void deleteFromCart(CartDTO request) throws Exception {
        //isAuthed
//        if (restTemplate.postForEntity(authUrl, makeAuthPostBody(new UserPair(request.getCookie(), request.getLogin())), Object.class).getStatusCode() != HttpStatus.OK)
//            throw new Exception("400");

        Cart cartToUpdate = cartRepository.findById(request.getLogin()).orElseThrow();
        cartToUpdate.getCartProducts().remove(request.getItemId());
        //get product json
        //cartToUpdate.getCartProducts().add(product);
        cartRepository.save(cartToUpdate);
    }

    public void buy(BuyDTO comparisonCart) throws Exception {
        //isAuthed
        if (restTemplate.postForEntity(authUrl,
                makeAuthPostBody(new UserPair(comparisonCart.getCookie(), comparisonCart.getCart().getOwnerLogin())),
                Object.class).getStatusCode() != HttpStatus.OK)
            throw new Exception("400");
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

}
