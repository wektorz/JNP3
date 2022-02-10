package januszex.shoppingcartservice.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyDTO {
    private String cookie;
    private Cart cart;
}
