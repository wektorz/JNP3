package januszex.favouritesservice.Favourite;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends CrudRepository<FavouriteProduct, String> {
}
