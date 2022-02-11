package januszex.shoppingcartservice.Cart;

import januszex.shoppingcartservice.DTO.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.TreeMap;

@Data
@RedisHash(timeToLive = 1800, value = "Cart")
@AllArgsConstructor
public class Cart implements Serializable {
    @Id
    private String ownerLogin;
    private TreeMap<String, ProductDTO> cartProducts;
}
