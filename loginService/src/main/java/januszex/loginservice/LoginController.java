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
public class LoginController {
    @Autowired
    private LoginService service;

    @PostMapping(
            value = "/auth",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = "application/json"
            )
    public ResponseEntity<String> authenticate(@RequestBody LoginAndCookie json)
    {
        return service.auth(json);
    }


    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<String> register(@RequestBody LoginAndPassword json)
    {
        return service.register(json);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<String> login(@RequestBody LoginAndPassword json)
    {
        return service.login(json);
    }
}
