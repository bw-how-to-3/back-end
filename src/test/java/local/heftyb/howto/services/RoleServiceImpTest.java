package local.heftyb.howto.services;

import local.heftyb.howto.HowToApplication;
import local.heftyb.howto.models.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HowToApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleServiceImpTest
{
    @Autowired
    private RoleService roleService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void a_findAll()
    {
        Role newRole = new Role("ADMIN");
        newRole = roleService.save(newRole);
        assertEquals(1, roleService.findAll().size());
    }

    @Test
    public void b_findRoleById()
    {
        assertEquals("ADMIN", roleService.findAll().get(0).getName());
    }

    @Test
    public void c_save()
    {
        Role role = new Role("test");
        role = roleService.save(role);

        assertEquals(2, roleService.findAll().size());
    }

    @Test
    public void d_findByName()
    {
        assertEquals("ADMIN", roleService.findByName("admin").getName());
    }
}