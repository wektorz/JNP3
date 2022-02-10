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
    private LoginAndCookieRepository loginAndCookieRepository;

    public ResponseEntity<String> login(LoginAndPassword login)
    {
        if (!login.validate()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        BooleanWrapper ok = new BooleanWrapper();

        loginAndPasswordRepository.findLoginAndPasswordByLoginAndHaslo(login.getLogin(), login.getHaslo())
                .ifPresentOrElse(
                        l -> { ok.set(true); },
                        ()->{ ok.set(false);  }
                );

        if(ok.get())
        {
            //correct login
            System.out.println("OK login");
            LoginAndCookie l = new LoginAndCookie(login.getLogin());

            loginAndCookieRepository.deleteLoginAndCookieByLogin(login.getLogin());

            loginAndCookieRepository.insert(l);
            return new ResponseEntity<>(l.toString(), HttpStatus.OK);
        }
        else
        {
            System.out.println("BAD login");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> register(LoginAndPassword login)
    {
        if (!login.validate()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        BooleanWrapper ok = new BooleanWrapper();

        loginAndPasswordRepository.findLoginAndPasswordByLogin(login.getLogin())
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

    public ResponseEntity<String> auth(LoginAndCookie login)
    {
        BooleanWrapper ok = new BooleanWrapper();

        loginAndCookieRepository.findLoginAndCookieByLoginAndCookie(login.getLogin(), login.getCookie())
                .ifPresentOrElse(
                        l -> { ok.set(true); },
                        ()->{ ok.set(false); }
                );

        if(ok.get())
        {
            System.out.println("OK auth");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            System.out.println("BAD auth");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    public ResponseEntity<String> logout(LoginAndCookie login)
    {
        BooleanWrapper ok = new BooleanWrapper();

        loginAndCookieRepository.findLoginAndCookieByLoginAndCookie(login.getLogin(), login.getCookie())
                .ifPresentOrElse(
                        l -> { ok.set(true); },
                        ()->{ ok.set(false); }
                );

        if(ok.get())
        {
            System.out.println("OK logout");
            loginAndCookieRepository.deleteLoginAndCookieByLogin(login.getLogin());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            System.out.println("BAD logout");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
