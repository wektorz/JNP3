package januszex.loginservice;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;

import java.util.Random;


@Data
@Document
@Sharded(shardKey = { "mod"} )
public class Product {
    @Id
    private String id;

    private Integer mod;

    private String desc;
    private float price;
    private String img;

    Product(String desc, float price, String img)
    {
        Random random = new Random();
        this.mod=  (random.nextInt() % 2 +2 ) % 2;
        this.desc=desc;
        this.price=price;
        this.img=img;
    }
}