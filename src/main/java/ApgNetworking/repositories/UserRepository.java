package ApgNetworking.repositories;

import ApgNetworking.models.ApgUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ApgUser,Long> {
    ApgUser findByUsername(String username);
    ApgUser findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);
}
