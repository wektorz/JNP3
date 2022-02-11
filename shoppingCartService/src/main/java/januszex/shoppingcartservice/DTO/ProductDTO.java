package januszex.shoppingcartservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ProductDTO {
    private String id;
    private String desc;
    private float price;
    private String img;
    private int quantity;
}
