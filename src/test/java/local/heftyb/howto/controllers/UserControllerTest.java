package local.heftyb.howto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import local.heftyb.howto.HowToApplication;
import local.heftyb.howto.models.User;
import local.heftyb.howto.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = HowToApplication.class)
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class UserControllerTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    List<User> userList = new ArrayList<>();

    @Before
    public void setUp() throws Exception
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(SecurityMockMvcConfigurers.springSecurity()).build();

        User user = new User("NewUser", "password");
        user.setUserid(50);
        userList.add(user);
    }

    @Test
    public void listAllUsers() throws Exception
    {
        String urls = "/users/users";
        Mockito.when(userService.findAll()).thenReturn(userList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(urls).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String tr = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList);

        assertEquals(er, tr);
    }

    @Test
    public void getuserByUsername() throws Exception
    {
        String urls = "/users/user/username/NewUser";

        Mockito.when(userService.findByUsername("NewUser")).thenReturn(userList.get(0));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(urls).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String tr = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList.get(0));

        assertEquals(er, tr);
    }

    @Test
    public void getUsersLike() throws Exception
    {
        String urls = "/users/user/username/like/New";
        Mockito.when(userService.findByNameContaining("New")).thenReturn(userList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(urls).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String tr = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList);

        assertEquals(er, tr);

    }

    @Test
    public void deleteUserById() throws Exception
    {
        String urls = "/users/user/50";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(urls);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}