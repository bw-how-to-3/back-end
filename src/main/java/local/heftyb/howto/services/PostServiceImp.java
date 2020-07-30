package local.heftyb.howto.services;

import local.heftyb.howto.exceptions.ResourceNotFoundException;
import local.heftyb.howto.models.Post;
import local.heftyb.howto.models.User;
import local.heftyb.howto.models.Vote;
import local.heftyb.howto.repository.PostRepository;
import local.heftyb.howto.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("postService")
public class PostServiceImp implements PostService
{

    @Autowired
    PostRepository postrepo;

    @Autowired
    VoteRepository voterepo;

    @Autowired
    UserService userService;

    @Override
    public List<Post> findAll()
    {
        List<Post> list = new ArrayList<>();
        postrepo.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Post findPostById(long id)
    {
        return postrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " Not Found!"));
    }

    @Override
    public Post findByTitle(String title)
    {
        Post post = postrepo.findByTitleIgnoreCase(title);
        if (post == null)
        {
            throw new ResourceNotFoundException("Post title " + title + " not Found!");
        }
        return post;
    }

    @Override
    public List<Post> findByTitleLike(String title)
    {
        return postrepo.findByTitleContainingIgnoreCase(title);
    }

    @Transactional
    @Override
    public Post save(Post post)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Post newPost = new Post();

        if (newPost.getPostid() != 0)
        {
            postrepo.findById(post.getPostid())
                .orElseThrow(() -> new ResourceNotFoundException("Post id " + post.getPostid() + " not Found!"));
            newPost.setPostid(post.getPostid());
        }

        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());

        if (authentication == null)
        {
            newPost.setUser(userService.findUserById(post.getUser()
                .getUserid()));
        } else
        {
            newPost.setUser(userService.findByUsername(authentication.getName()));
        }

        newPost.setUpvotes(post.getUpvotes());
        newPost.setDownvotes(post.getDownvotes());


        for (Vote v :
            post.getVotes())
        {
            User addUser = userService.findUserById(v.getUser().getUserid());
            newPost.getVotes().add(new Vote(addUser, newPost));
        }


        return postrepo.save(newPost);
    }

    @Transactional
    @Override
    public Post update(
        Post post,
        long id)
    {
        Post currentPost = findPostById(id);

        if (post.getTitle() != null)
        {
            currentPost.setTitle(post.getTitle());
        }

        if (post.getBody() != null)
        {
            currentPost.setBody(post.getBody());
        }

        return postrepo.save(currentPost);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        postrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Post id " + id + " not found!"));
        postrepo.deleteById(id);
    }

    @Override
    public List<Post> findUsersPost()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<Post> userPosts = userService.findByUsername(authentication.getName()).getPosts();
        return userPosts;
    }

    @Override
    public void downvote(long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Post votedPost = findPostById(id);
        User user = userService.findByUsername(authentication.getName());

        votedPost.setDownvotes(votedPost.getDownvotes() + 1);

        Vote newVote = new Vote(user, votedPost);

        votedPost.getVotes().add(newVote);
        user.getVotedPost().add(newVote);
    }

    @Override
    public void upvote(long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Post votedPost = findPostById(id);
        User user = userService.findByUsername(authentication.getName());

        votedPost.setUpvotes(votedPost.getUpvotes() + 1);

        Vote newVote = new Vote(user, votedPost);

        votedPost.getVotes().add(newVote);
        user.getVotedPost().add(newVote);
    }
}
