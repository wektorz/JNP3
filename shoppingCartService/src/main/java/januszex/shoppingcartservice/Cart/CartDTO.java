package januszex.shoppingcartservice.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CartDTO implements Serializable {
    private String cookie;
    private String login;
    private String itemId;
    private int quantity;
}
