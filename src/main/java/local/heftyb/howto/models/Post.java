package local.heftyb.howto.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    private int upvotes = 0;

    private int downvotes = 0;

    @OneToMany(mappedBy = "post",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "post",
        allowSetters = true)
    private Set<Vote> votes = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("posts")
    private User user;

    public Post()
    {
    }

    public Post(
        String title,
        String body)
    {
        this.title = title;
        this.body = body;
    }

    public long getPostid()
    {
        return postid;
    }

    public void setPostid(long postid)
    {
        this.postid = postid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public int getUpvotes()
    {
        return upvotes;
    }

    public void setUpvotes(int upvotes)
    {
        this.upvotes = upvotes;
    }

    public int getDownvotes()
    {
        return downvotes;
    }

    public void setDownvotes(int downvotes)
    {
        this.downvotes = downvotes;
    }

    public Set<Vote> getVotes()
    {
        return votes;
    }

    public void setVotes(Set<Vote> votes)
    {
        this.votes = votes;
    }

    public void addVotes(Vote vote)
    {
        votes.add(vote);
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "Post{" +
            "postid=" + postid +
            ", title='" + title + '\'' +
            ", body='" + body + '\'' +
            ", upvotes=" + upvotes +
            ", downvotes=" + downvotes +
            ", votes=" + votes +
            ", user=" + user +
            '}';
    }
}
