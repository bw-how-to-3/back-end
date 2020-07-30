package local.heftyb.howto.services;

import local.heftyb.howto.HowToApplication;
import local.heftyb.howto.models.Post;
import local.heftyb.howto.models.User;
import local.heftyb.howto.models.Vote;
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
public class PostServiceImpTest
{
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

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
        assertEquals(1, postService.findAll().size());
    }

    @Test
    public void b_findPostById()
    {
        assertEquals("TEST", postService.findPostById(postService.findAll().get(0).getPostid()).getTitle());
    }

    @Test
    public void c_findByTitle()
    {
        assertEquals("TEST", postService.findByTitle("TEST").getTitle());
    }

    @Test
    public void d_findByTitleLike()
    {
        assertEquals(1, postService.findByTitleLike("TE").size());
    }

    @Test
    public void e_save()
    {
        User user = userService.findAll().get(0);
        Post post = new Post("NEW POST", "THIS IS THE NEW POST, STAND UP AND ADMIT");
        post.setUser(user);
        post = postService.save(post);

        assertEquals(2, postService.findAll().size());
    }

    @Test
    public void z_delete()
    {
        postService.delete(postService.findAll().get(1).getPostid());
        assertEquals(1, postService.findAll().size());
    }

    @Test
    public void f_findUsersPost()
    {
        assertEquals(1, userService.findAll().get(0).getPosts().size());
    }

    @Test
    public void g_downvote()
    {
        Post votedPost = postService.findAll().get(0);
        User user = userService.findAll().get(0);

        votedPost.setDownvotes(votedPost.getDownvotes() + 1);

        Vote newVote = new Vote(user, votedPost);

        votedPost.getVotes().add(newVote);
        user.getVotedPost().add(newVote);

        assertEquals(1, user.getVotedPost().size());
    }

    @Test
    public void h_upvote()
    {
        Post votedPost = postService.findAll().get(0);
        User user = userService.findAll().get(0);

        votedPost.setUpvotes(votedPost.getUpvotes() + 1);

        Vote newVote = new Vote(user, votedPost);

        votedPost.getVotes().add(newVote);
        user.getVotedPost().add(newVote);

        assertEquals(2, user.getVotedPost().size());
    }
}