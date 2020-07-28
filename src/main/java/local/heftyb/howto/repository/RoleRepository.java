package local.heftyb.howto.repository;

import local.heftyb.howto.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>
{
    Role findByNameIgnoreCase(String name);
}
