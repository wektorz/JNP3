package januszex.favouritesservice.Favourite;

import januszex.favouritesservice.DTO.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.TreeMap;

@Data
@RedisHash(value = "Favourite")
@AllArgsConstructor
public class FavouriteProduct implements Serializable {
    @Id
    private String ownerLogin;
    private TreeMap<String, ProductDTO> favouriteProducts;

}
