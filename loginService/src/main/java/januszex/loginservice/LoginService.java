package januszex.loginservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.HttpMessageConverter;

@Service
public class LoginService {

    public ResponseEntity<String> login(LoginAndPassword login)
    {

        System.out.println(login.toString());
        return new ResponseEntity<>(login.toString(), HttpStatus.OK);
    }
}
