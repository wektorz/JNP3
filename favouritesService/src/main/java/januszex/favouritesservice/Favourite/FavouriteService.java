package januszex.favouritesservice.Favourite;

import januszex.favouritesservice.DTO.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(FavouriteService.class);
    private final String authUrl = "http://login:8080/api/auth";
    private final String productUrl = "http://products:8080/api/products";

    @Autowired
    public FavouriteService(FavouriteRepository cartRepository, RestTemplateBuilder restTemplateBuilder){
        this.favouriteRepository = cartRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    public ArrayList<ProductDTO> getFavourites(UserPair user) throws Exception {
        //isAuthed
        if (restTemplate.postForEntity(authUrl, makeAuthPostBody(user), Object.class).getStatusCode() != HttpStatus.OK)
           throw new Exception("400");

        FavouriteProduct favouritesToGet = favouriteRepository.findById(user.getLogin()).orElseThrow();
        logger.error(favouritesToGet.toString());
        return new ArrayList<>(favouritesToGet.getFavouriteProducts().values());
    }

    public void addToFavourites(FavouriteDTO request) throws Exception {
        //isAuthed
        if (restTemplate.postForEntity(authUrl, makeAuthPostBody(new UserPair(request.getCookie(), request.getLogin())), Object.class).getStatusCode() != HttpStatus.OK)
            throw new Exception("400");

        //if no cart present build new one
        FavouriteProduct favouriteToUpdate = favouriteRepository.findById(request.getLogin()).orElse(new FavouriteProduct(request.getLogin(), new TreeMap<>()));
        if(favouriteToUpdate.getFavouriteProducts() == null)
            favouriteToUpdate.setFavouriteProducts(new TreeMap<>());

        ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(productUrl + String.format("?id=%s", request.getItemId()), ProductDTO[].class);
        if(response.getBody() == null)
            throw new Exception("400");

        favouriteToUpdate.getFavouriteProducts().putIfAbsent(request.getItemId(), response.getBody()[0]);
        favouriteRepository.save(favouriteToUpdate);
    }


    public void deleteFromFavourites(FavouriteDTO request) throws Exception {
        //isAuthed
        if (restTemplate.postForEntity(authUrl, makeAuthPostBody(new UserPair(request.getCookie(), request.getLogin())), Object.class).getStatusCode() != HttpStatus.OK)
            throw new Exception("400");

        FavouriteProduct favouriteToUpdate = favouriteRepository.findById(request.getLogin()).orElseThrow();
        favouriteToUpdate.getFavouriteProducts().remove(request.getItemId());
        favouriteRepository.save(favouriteToUpdate);
    }

    public void compare(CompareDTO cart) throws Exception {
        //isAuthed
        if (restTemplate.postForEntity(authUrl, makeAuthPostBody(new UserPair(cart.getCookie(), cart.getLogin())),
                Object.class).getStatusCode() != HttpStatus.OK)
            throw new Exception("400");
    }


    private HttpEntity<Map<String, Object>> makeAuthPostBody(UserPair user){
        Map<String, Object> entries = new HashMap<>();
        entries.put("login", user.getLogin());
        entries.put("cookie", user.getCookie());
        return new HttpEntity<>(entries);
    }
}