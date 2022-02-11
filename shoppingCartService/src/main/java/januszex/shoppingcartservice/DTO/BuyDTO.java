package januszex.shoppingcartservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class BuyDTO {
    private String cookie;
    private String login;
    private ArrayList<ProductDTO> cart;
}
