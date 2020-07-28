package local.heftyb.howto;

import local.heftyb.howto.models.Post;
import local.heftyb.howto.models.Role;
import local.heftyb.howto.models.User;
import local.heftyb.howto.models.UserRoles;
import local.heftyb.howto.services.PostService;
import local.heftyb.howto.services.RoleService;
import local.heftyb.howto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    RoleService roleService;

    @Transactional
    @Override
    public void run(String... args) throws Exception
    {
        Role r1 = new Role("ADMIN");
        Role r2 = new Role("USER");

        roleService.save(r1);
        roleService.save(r2);

        User u1 = new User("admin",
            "password");
        u1.addRole(new UserRoles(u1,
            r1));

        Post p1 = new Post("TEST",
            "TESTING, 123....., TESTING, 123");
        p1.setUser(u1);
        u1.addPost(p1);
        //                System.out.println(p1);
        //                System.out.println(u1);
        //                postService.save(p1);
        //                userService.save(u1);

    }
}
