package januszex.favouritesservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class FavouriteDTO implements Serializable {
    private String cookie;
    private String login;
    private String itemId;
}
