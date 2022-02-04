package januszex.shoppingcartservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingControllerTest {

    @GetMapping("/api/shop")
    public String test()
    {
        return "Szop Pracz";
    }
}
