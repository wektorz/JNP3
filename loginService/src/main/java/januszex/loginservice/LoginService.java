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

    private LoginAndPasswordRepository loginAndPasswordRepository;

    public ResponseEntity<String> login(LoginAndPassword login)
    {

        System.out.println(login.toString());
        return new ResponseEntity<>(login.toString(), HttpStatus.OK);
    }

    public ResponseEntity<String> register(LoginAndPassword login)
    {
        if (!login.validate()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        System.out.println("OK register");

        BooleanWrapper ok = new BooleanWrapper();

        loginAndPasswordRepository.findLoginAndPasswordByLogin("jan")
                .ifPresentOrElse(
                l -> { ok.set(false); },
                ()->{ loginAndPasswordRepository.insert(login); }
        );


        if(ok.get())
        {
            System.out.println("OK register");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            System.out.println("BAD register");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> auth(LoginAndPassword json)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
