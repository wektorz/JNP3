package januszex.loginservice;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.security.SecureRandom;
import java.util.Optional;

public interface LoginAndPasswordRepository extends MongoRepository<LoginAndPassword, String> {

    Optional<LoginAndPassword> findLoginAndStudentByLogin(String login);
}
