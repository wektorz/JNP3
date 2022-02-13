package januszex.shoppingcartservice.Cart;

import januszex.shoppingcartservice.DTO.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(CartService.class);
    private final String authUrl = "http://login:8080/api/auth";
    private final String productUrl = "http://products:80/api/products";

    @Autowired
    public CartService(CartRepository cartRepository, RestTemplateBuilder restTemplateBuilder){
        this.cartRepository = cartRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    public ArrayList<ProductDTO> getCart(UserPair user) throws Exception {
        //isAuthed
        if (restTemplate.postForEntity(authUrl, makeAuthPostBody(user), Object.class).getStatusCode() != HttpStatus.OK)
           throw new Exception("400");

        Cart cartToGet = cartRepository.findById(user.getLogin()).orElseThrow();
        return new ArrayList<>(cartToGet.getCartProducts().values());
    }

    public void addToCart(CartDTO request) throws Exception {
        //isAuthed
        if (restTemplate.postForEntity(authUrl, makeAuthPostBody(new UserPair(request.getCookie(), request.getLogin())), Object.class).getStatusCode() != HttpStatus.OK)
            throw new Exception("400");

        //if no cart present build new one
        Cart cartToUpdate = cartRepository.findById(request.getLogin()).orElse(new Cart(request.getLogin(), new TreeMap<>()));
        if(cartToUpdate.getCartProducts() == null)
            cartToUpdate.setCartProducts(new TreeMap<>());

        if(cartToUpdate.getCartProducts().containsKey(request.getItemId())) {
            cartToUpdate.getCartProducts().computeIfPresent(request.getItemId(),
                    (key, value) -> new ProductDTO(value.getId(), value.getDesc(), value.getPrice(), value.getImg(), value.getQuantity() + request.getQuantity()));
            cartRepository.save(cartToUpdate);
            return;
        }

        ResponseEntity<ProductServiceDTO[]> response = restTemplate.getForEntity(productUrl + String.format("?id=%s", request.getItemId()), ProductServiceDTO[].class);
        if(response.getBody() == null)
            throw new Exception("400");
        ProductDTO product = mapToProductDTO(response.getBody()[0], request.getQuantity());

        cartToUpdate.getCartProducts().putIfAbsent(request.getItemId(), product);
        cartRepository.save(cartToUpdate);
    }

    private ProductDTO mapToProductDTO(ProductServiceDTO productServiceDTO, int q) {
        return new ProductDTO(productServiceDTO.getId(),
                productServiceDTO.getDesc(),
                productServiceDTO.getPrice(),
                productServiceDTO.getImg(), q);
    }

    public void deleteFromCart(CartDTO request) throws Exception {
        //isAuthed
        if (restTemplate.postForEntity(authUrl, makeAuthPostBody(new UserPair(request.getCookie(), request.getLogin())), Object.class).getStatusCode() != HttpStatus.OK)
            throw new Exception("400");

        Cart cartToUpdate = cartRepository.findById(request.getLogin()).orElseThrow();
        if (cartToUpdate.getCartProducts().get(request.getItemId()).getQuantity() <= request.getQuantity())
            cartToUpdate.getCartProducts().remove(request.getItemId());
        else
            cartToUpdate.getCartProducts().computeIfPresent(request.getItemId(),
                    (key, value) -> new ProductDTO(value.getId(), value.getDesc(), value.getPrice(), value.getImg(), value.getQuantity() - request.getQuantity()));
        cartRepository.save(cartToUpdate);
    }

    public void buy(BuyDTO cart) throws Exception {
        //isAuthed
        if (restTemplate.postForEntity(authUrl, makeAuthPostBody(new UserPair(cart.getCookie(), cart.getLogin())),
                Object.class).getStatusCode() != HttpStatus.OK)
            throw new Exception("400");

        if (!compareCarts(cartRepository.findById(cart.getLogin()).orElseThrow().getCartProducts(), cart.getCart()))
            throw new Exception("400");
        sendToVendors(cart);
    }


    private HttpEntity<Map<String, Object>> makeAuthPostBody(UserPair user){
        Map<String, Object> entries = new HashMap<>();
        entries.put("login", user.getLogin());
        entries.put("cookie", user.getCookie());
        return new HttpEntity<>(entries);
    }

    private boolean compareCarts(TreeMap<String, ProductDTO> baseCart, ArrayList<ProductDTO> comparisonCart){
        logger.error("Comparing");
        for (ProductDTO product: comparisonCart){
            if(product.getQuantity() <= 0)
                continue;
            if(!baseCart.get(product.getId()).equals(product))
                return false;
        }
        return true;
    }

    private void sendToVendors(BuyDTO cart) {
        cartRepository.deleteById(cart.getLogin());
    }
}
