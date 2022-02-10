package januszex.shoppingcartservice.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.minidev.json.JSONObject;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class BuyDTO {
    private String cookie;
    private String login;
    private ArrayList<ProductDTO> cart;
}
