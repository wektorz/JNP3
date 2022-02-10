package januszex.loginservice;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.SecureRandom;
import java.util.Random;

@Data
@Document
public class LoginAndCookie {
    @Id
    private String id;

    @Indexed(unique = true)
    private String login;
    private String cookie;

    LoginAndCookie()
    {
    }

    LoginAndCookie(String login)
    {
        this.login=login;
        char[] s= new char[20];
        Random random = new SecureRandom();
        for(int i=0;i<20;i++)
        {
            s[i] = (char) (random.nextInt(24)+65);
        }
        this.cookie= new String(s);
    }

    @Override
    public String toString() {
        return "{" +
                "\"login\": \"" + login + "\"," +
                "\"cookie\": \"" + cookie + "\"" +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public String getCookie() {
        return cookie;
    }
}