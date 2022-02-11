package januszex.loginservice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findProductById(String id);
}
