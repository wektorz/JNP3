package januszex.favouritesservice.Favourite;

import januszex.favouritesservice.DTO.CompareDTO;
import januszex.favouritesservice.DTO.FavouriteDTO;
import januszex.favouritesservice.DTO.ProductDTO;
import januszex.favouritesservice.DTO.UserPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/fav")
public class FavouriteController {

    private final FavouriteService favouriteService;
    private final Logger logger = LoggerFactory.getLogger(FavouriteController.class);

    @Autowired
    public FavouriteController(FavouriteService cartService){
        this.favouriteService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getFavourites(@RequestParam String cookie, @RequestParam String login) {
        try {
            return new ResponseEntity<>(favouriteService.getFavourites(new UserPair(cookie, login)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<String> addToFavourites(@RequestBody FavouriteDTO request){
        logger.error(request.toString());
        try {
            favouriteService.addToFavourites(request);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFromFavourites(@RequestBody FavouriteDTO request){
        try {
            favouriteService.deleteFromFavourites(request);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sort")
    public ResponseEntity<List<ProductDTO>> sort(@RequestParam String cookie, @RequestParam String login, @RequestParam String type){
        try {
            return new ResponseEntity<>(favouriteService.getFavouritesSorted(new UserPair(cookie, login), type), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
