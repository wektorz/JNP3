package januszex.shoppingcartservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ProductServiceDTO {
    private String id;
    private String desc;
    private float price;
    private String img;
}
