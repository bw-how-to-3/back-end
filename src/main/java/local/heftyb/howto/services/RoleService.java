package local.heftyb.howto.services;

import local.heftyb.howto.models.Role;

import java.util.List;

public interface RoleService
{
    List<Role> findAll();

    Role findRoleById(long id);

    Role save(Role role);

    Role findByName(String name);

    Role update(
        long id,
        Role role);
}
