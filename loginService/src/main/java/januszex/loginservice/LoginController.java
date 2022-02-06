package januszex.loginservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping(
            value = "/auth",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
            )
    public ResponseEntity<String> authenticate(@RequestBody LoginAndPassword json)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(
            value = "/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> register(@RequestBody LoginAndPassword json)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> login(@RequestBody LoginAndPassword json)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
