package januszex.loginservice;


import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;



@SpringBootApplication
public class ProductsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductRepository repository, MongoTemplate template){
        return args->{

            MongoDatabase adminDB = template.getMongoDbFactory()
                    .getMongoDatabase("admin");

            adminDB.runCommand(new Document("enableSharding", "mydatabase"));

            Document shardCmd = new Document("shardCollection", "mydatabase.product")
                    .append("key", new Document("mod", 1));

            adminDB.runCommand(shardCmd);


            Product p = new Product("Widelec do lodów",12F,"https://www.carlroth.com/medias/YC43-1000Wx1000H?context=bWFzdGVyfGltYWdlc3w1NzMwM3xpbWFnZS9qcGVnfGltYWdlcy9oZTMvaGRhLzg4MjIwMzUzMTY3NjYuanBnfGUzNTlkMGM2OWEyNjAwMTZmMWY2ZWE0MzUwZTRmMGQ0MGU0MDE3OGFmNDg1Njg4MTJhYzFlMmI1ZmY4NmI3OTY");
            repository.insert(p);
            p = new Product("Łyżka",13.37F,"https://boma-agd.pl/pol_pm_La-Torre-Inglese-lyzka-nickel-free-553_1.jpg");
            repository.insert(p);
            p = new Product("Fartuch",30F,"https://www.ikea.com/us/en/images/products/sandviva-apron-blue__0789451_pe763993_s5.jpg?f=s");
            repository.insert(p);
            p= new Product("Stól",130F,"https://www.ikea.com/pl/pl/images/products/laneberg-stol-rozkladany-bialy__0722925_pe733775_s5.jpg?f=s");
            repository.insert(p);
            p= new Product("Krzesło",69.99F,"https://e-meblowy.pl/8483-thickbox_default/krzesla-z-drewna-bukowego-mdf-dariusz.jpg");
            repository.insert(p);
            p= new Product("talerz", 4.99F,"https://sklep.sokomedica.pl/pol_pl_TALERZE-ZESTAW-OBIADOWY-SERWIS-18-el-TALERZY-26149992_11.jpg");
            repository.insert(p);
            p =new Product("Zestaw sztućców", 12F,"https://kuchniasklep.pl/images/Zwilling/mini/webp/500px_crop_zestaw-sztuccow-king-100el-1.webp");
            repository.insert(p);
        };
    }

}
