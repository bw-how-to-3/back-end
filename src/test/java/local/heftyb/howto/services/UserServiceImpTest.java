package local.heftyb.howto.services;

import local.heftyb.howto.HowToApplication;
import local.heftyb.howto.models.Post;
import local.heftyb.howto.models.User;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HowToApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImpTest
{
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    @org.junit.Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.After
    public void tearDown() throws Exception
    {
    }

    @org.junit.Test
    public void c_findAll()
    {
        assertEquals(3, userService.findAll().size());
    }

    @org.junit.Test
    public void b_findByNameContaining()
    {
        assertEquals(2, userService.findByNameContaining("use").size());
    }

    @org.junit.Test
    public void d_findByUsername() throws Exception
    {
        System.out.println(userService.findByUsername("User1").getUserid());
        assertEquals("User1", userService.findByUsername("User1").getUsername());
    }

    @org.junit.Test
    public void e_findUserById()
    {
        assertEquals("admin", userService.findUserById(3).getUsername());
    }

    @org.junit.Test
    public void f_delete()
    {
        userService.delete(3);
        assertEquals(2, userService.findAll().size());
    }

    @org.junit.Test
    public void a_save() throws Exception
    {
        User newUser = new User("User1", "password");
        User newUser2 = new User("User2", "passwd");
        newUser = userService.save(newUser);
        newUser2 = userService.save(newUser2);
        Post p1 = new Post("TEST",
            "TESTING, 123....., TESTING, 123");
        p1.setUser(newUser);

        p1 = postService.save(p1);
        assertEquals(3, userService.findAll().size());
    }
}