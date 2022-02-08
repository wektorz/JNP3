package januszex.loginservice;

public class LoginAndPassword {
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
        return "LoginAndPassword{" +
                "login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                '}';
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
