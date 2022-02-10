package januszex.loginservice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface LoginAndPasswordRepository extends MongoRepository<LoginAndPassword, String> {

    Optional<LoginAndPassword> findLoginAndPasswordByLogin(String login);
}
