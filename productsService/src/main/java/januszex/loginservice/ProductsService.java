package januszex.loginservice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductsService {

    private ProductRepository productRepository;

    public ResponseEntity<ArrayList<Product>> getproducts(Optional<String> id) {
        if(id.isPresent())
        {
            Optional<Product>  product = productRepository.findProductById(id.get());
            return product.map(value -> new ResponseEntity<>(new ArrayList<>(List.of(value)), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
        else
        {
            return new ResponseEntity<>(new ArrayList<>(productRepository.findAll()), HttpStatus.OK);
        }
    }

}
