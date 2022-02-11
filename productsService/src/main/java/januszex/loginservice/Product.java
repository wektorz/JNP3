package januszex.loginservice;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Product {
    @Id
    private String id;

    private String desc;
    private float price;
    private String img;

    Product(String desc, float price, String img)
    {
        this.desc=desc;
        this.price=price;
        this.img=img;
    }
}