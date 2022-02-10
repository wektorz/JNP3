package januszex.shoppingcartservice.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@RedisHash("Cart")
@AllArgsConstructor
public class Cart implements Serializable {
    @Id
    private int ownerId;
    private ArrayList<String> cartProducts;
}
