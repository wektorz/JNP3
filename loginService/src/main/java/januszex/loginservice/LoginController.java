package januszex.loginservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/auth")
    public ResponseEntity<String> authenticate()
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/register")
    public ResponseEntity<String> register()
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login()
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
