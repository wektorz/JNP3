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
    private String price;
    private String img;

    Product()
    {
    }

    Product(String desc,String price,String img)
    {
        this.desc=desc;
        this.price=price;
        this.img=img;
    }

    @Override
    public String toString() {
        return "{" +
                "\"desc\": \"" + desc + "\"," +
                "\"price\": \"" + price + "\"," +
                "\"img\": \"" + img + "\"" +
                '}';
    }

}