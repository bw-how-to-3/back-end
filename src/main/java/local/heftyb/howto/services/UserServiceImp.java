package local.heftyb.howto.services;

import local.heftyb.howto.exceptions.ResourceNotFoundException;
import local.heftyb.howto.models.Role;
import local.heftyb.howto.models.User;
import local.heftyb.howto.models.UserRoles;
import local.heftyb.howto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImp implements UserService
{
    @Autowired
    private UserRepository userrepo;

    @Autowired
    RoleService roleService;

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        userrepo.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<User> findByNameContaining(String username)
    {
        return userrepo.findByUsernameContainingIgnoreCase(username);
    }

    @Override
    public User findByUsername(String username)
    {
        User user = userrepo.findByUsernameIgnoreCase(username);
        if (user == null)
        {
            throw new ResourceNotFoundException("User name " + username + " not found!");
        }
        return user;
    }

    @Override
    public User findUserById(long id) throws ResourceNotFoundException
    {
        return userrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id) throws ResourceNotFoundException
    {
        userrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User user1 = new User();

        if (user.getUserid() != 0)
        {
            userrepo.findById(user.getUserid())
                .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));
            user1.setUserid(user.getUserid());
        }

        user1.setUsername(user.getUsername());
        user1.setPasswordNoEncrypt(user.getPassword());

        for (UserRoles ur :
            user.getRoles())
        {
            Role newRole = roleService.findRoleById(ur.getRole()
                .getRoleid());
            user1.getRoles()
                .add(new UserRoles(user1,
                    newRole));
        }
        return null;
    }

    @Transactional
    @Override
    public User update(
        User user,
        long id)
    {
        return null;
    }
}
