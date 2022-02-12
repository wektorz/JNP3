package januszex.favouritesservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class CompareDTO {
    private String cookie;
    private String login;
    private ArrayList<ProductDTO> products;
}
