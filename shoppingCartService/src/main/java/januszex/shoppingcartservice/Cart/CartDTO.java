package januszex.shoppingcartservice.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CartDTO implements Serializable {
    private int userId;
    private int itemId;
    private int quantity;
}
