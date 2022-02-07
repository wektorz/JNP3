package januszex.loginservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService service;

    @PostMapping(
            value = "/auth",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
            )
    public ResponseEntity<String> authenticate(@RequestBody LoginAndPassword json)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody LoginAndPassword json)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginAndPassword json)
    {
        return service.login(json);
    }
}
