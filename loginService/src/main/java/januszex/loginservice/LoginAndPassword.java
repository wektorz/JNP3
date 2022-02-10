package januszex.loginservice;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import static ch.qos.logback.core.joran.action.ActionConst.NULL;

@Data
@Document
public class LoginAndPassword {
    @Id
    private String id;

    @Indexed(unique = true)
    private String login;
    private String haslo;

    public LoginAndPassword(String login, String haslo)
    {
        this.login=login;
        this.haslo=haslo;
    }

    public LoginAndPassword()
    {
    }

    @Override
    public String toString() {
        return "{" +
                "\"login\": \"" + login + "\"," +
                "\"haslo\": \"" + haslo + "\"" +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public boolean validate()
    {
        return login != NULL && haslo!= NULL;
    }
}
