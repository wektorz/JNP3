package januszex.loginservice;

import java.security.SecureRandom;
import java.util.Random;

public class LoginAndCookie {
    private String login;
    private String cookie;

    LoginAndCookie(String login, String cookie)
    {
        this.login=login;
        this.cookie=cookie;
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
}