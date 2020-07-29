package local.heftyb.howto.repository;

import local.heftyb.howto.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsernameIgnoreCase(String username);

    List<User> findByUsernameContainingIgnoreCase(String username);
}
