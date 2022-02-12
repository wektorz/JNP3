package januszex.favouritesservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPair {
    private String cookie;
    private String login;
}
