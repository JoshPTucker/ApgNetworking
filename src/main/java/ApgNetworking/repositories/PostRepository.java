package ApgNetworking.repositories;

import ApgNetworking.models.ApgPost;
import ApgNetworking.models.ApgUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PostRepository extends CrudRepository<ApgPost,Long> {
Collection<ApgPost> findApgPostsByApguser(ApgUser user);

}
