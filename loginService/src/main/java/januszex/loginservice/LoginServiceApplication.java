package januszex.loginservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoginServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginServiceApplication.class, args);
    }

  /*  @Bean
    CommandLineRunner createLoginAndPassword(LoginAndPasswordRepository repository, LoginAndPassword login){
        return args->{
            repository.insert(login);
        };
    }*/

    @Bean
    CommandLineRunner runner(LoginAndPasswordRepository repository){
        return args->{
            LoginAndPassword login = new LoginAndPassword("jan","pawel");
            repository.insert(login);
        };
    }
}
