package januszex.loginservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductsService service;

    /*
    @PostMapping(
            value = "/auth",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = "application/json"
            )
    public ResponseEntity<String> authenticate(@RequestBody LoginAndCookie json)
    {
        return service.auth(json);
    }*/



}
