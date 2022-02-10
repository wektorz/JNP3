package januszex.loginservice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.HttpMessageConverter;

@AllArgsConstructor
@Service
public class LoginService {
    //private final LoginAndPasswordRepository loginAndPasswordRepository;

    public ResponseEntity<String> login(LoginAndPassword login)
    {

        System.out.println(login.toString());
        return new ResponseEntity<>(login.toString(),headers, HttpStatus.OK);
    }

    public ResponseEntity<String> register(LoginAndPassword json)
    {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> auth(LoginAndPassword json)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
