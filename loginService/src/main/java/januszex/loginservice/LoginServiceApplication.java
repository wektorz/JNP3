package januszex.loginservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

@SpringBootApplication
public class LoginServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(LoginAndPasswordRepository repository, MongoTemplate mongoTemplate){
        return args->{
            LoginAndPassword login = new LoginAndPassword("jan","pawel");
            /*
            Query query = new Query();
            query.addCriteria(Criteria.where("login").is("jan"));
            List<LoginAndPassword> users = mongoTemplate.find(query, LoginAndPassword.class);

            if(users.isEmpty())
            {
                repository.insert(login);
            }
            else
            {
                System.out.println("duplicate login: " + login.getLogin() );
            }*/

            repository.findLoginAndPasswordByLogin("jan").ifPresentOrElse(
                    l -> { System.out.println("duplicate login ");},
                    ()->{ repository.insert(login);}
            );
        };
    }
}
