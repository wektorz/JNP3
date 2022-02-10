package januszex.loginservice;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LoginAndCookieRepository extends MongoRepository<LoginAndCookie, String>
{
        Optional<LoginAndCookie> findLoginAndCookieByLoginAndCookie(String login, String cookie);
        Optional<LoginAndCookie> deleteLoginAndCookieByLogin(String login);
}
