package januszex.loginservice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductsService {

    private ProductRepository productRepository;

    private String ProductListToString(List<Product > products)
    {
        String s = new String("{products: [");
        for(Product p : products)
        {
            s+= p.toString()+",";
        }
        s+="]}";
        return  s;
    }

    public ResponseEntity<String> getproducts(Optional<String> id) {
        if(id.isPresent())
        {
            Optional<Product>  product = productRepository.findProductById(id.get());
            if(product.isPresent()) {
                return new ResponseEntity<>(product.get().toString(), HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        else
        {
            List<Product > products = productRepository.findAll();
            return new ResponseEntity<>(ProductListToString(products), HttpStatus.OK);
        }

    }

    /*
    public ResponseEntity<String> login(LoginAndPassword login)
    {
        if (!login.validate()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        BooleanWrapper ok = new BooleanWrapper();

        loginAndPasswordRepository.findLoginAndPasswordByLoginAndHaslo(login.getLogin(), login.getHaslo())
                .ifPresentOrElse(
                        l -> { ok.set(true); },
                        ()->{ ok.set(false);  }
                );

    }
    */

}
