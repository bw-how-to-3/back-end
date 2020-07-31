package local.heftyb.howto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import local.heftyb.howto.HowToApplication;
import local.heftyb.howto.models.Post;
import local.heftyb.howto.models.User;
import local.heftyb.howto.models.UserRoles;
import local.heftyb.howto.services.PostService;
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
public class PostControllerTest
{

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    List<Post> postList = new ArrayList<>();

    @Before
    public void setUp() throws Exception
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(SecurityMockMvcConfigurers.springSecurity()).build();

        User u1 = new User("User1",
            "password");

        u1.setUserid(10);


        Post p1 = new Post("TEST",
            "TESTING, 123....., TESTING, 123");
        p1.setUser(u1);


        p1.setPostid(10);

        u1.addPost(p1);

        postList.add(p1);
    }

    @Test
    public void listAllPost() throws Exception
    {
        String urls = "/posts/posts";
        Mockito.when(postService.findAll()).thenReturn(postList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(urls).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String tr = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(postList);

        assertEquals(er, tr);
    }

    @Test
    public void listUsersPost() throws Exception
    {
        String urls = "/posts/user";
        Mockito.when(postService.findUsersPost()).thenReturn(postList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(urls).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String tr = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(postList);

        assertEquals(er, tr);
    }

    @Test
    public void addNewPost() throws Exception
    {
        String url = "/posts/post";

        Post newPost = new Post("NEW POST", "THIS IS THE NEW POST< SANDUP AND ADMI");
        newPost.setPostid(100);

        ObjectMapper mapper = new ObjectMapper();
        String postString = mapper.writeValueAsString(newPost);

        Mockito.when(postService.save(any(Post.class))).thenReturn(newPost);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(url).accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON).content(postString);

        mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateFullPost() throws Exception
    {
        String urls = "/posts/post/4";
        Post updatePost = new Post("TESSSTT", "A TYPO");
        updatePost.setPostid(4);

        ObjectMapper mapper = new ObjectMapper();
        String postString = mapper.writeValueAsString(updatePost);

        Mockito.when(postService.save(any(Post.class))).thenReturn(updatePost);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(urls).accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON).content(postString);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deletePostById() throws Exception
    {
        String urls = "/posts/post/100";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(urls);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void voteOnPost() throws Exception
    {
        String urls = "/posts/post/vote/105";
        Post votedPost = new Post("VOTE", "VOTED POST");
        votedPost.setPostid(105);

        ObjectMapper mapper = new ObjectMapper();
        String postString = mapper.writeValueAsString(false);

        Mockito.when(postService.findPostById(105)).thenReturn(votedPost);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(urls).accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON).content(postString);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void voteOnPost2() throws Exception
    {
        String urls = "/posts/post/vote/106";
        Post votedPost = new Post("VOTE", "VOTED POST");
        votedPost.setPostid(106);

        ObjectMapper mapper = new ObjectMapper();
        String postString = mapper.writeValueAsString(true);

        Mockito.when(postService.findPostById(106)).thenReturn(votedPost);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(urls).accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON).content(postString);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}