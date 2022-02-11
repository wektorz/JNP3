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
public class ProductsService {

    private ProductRepository productRepository;

    /*
    public ResponseEntity<String> login(LoginAndPassword login)
    {
        if (!login.validate()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        BooleanWrapper ok = new BooleanWrapper();

        loginAndPasswordRepository.findLoginAndPasswordByLoginAndHaslo(login.getLogin(), login.getHaslo())
                .ifPresentOrElse(
                        l -> { ok.set(true); },
                        ()->{ ok.set(false);  }
                );

    }
    */

}
