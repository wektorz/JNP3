package januszex.shoppingcartservice.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private String id;
    private String desc;
    private int price;
    private String img;
    private int quantity;
}
