package local.heftyb.howto.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "votes")
@IdClass(VoteId.class)
public class Vote extends Auditable implements Serializable
{

    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("votedpost")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "postid")
    @JsonIgnoreProperties("votes")
    private Post post;

    public Vote()
    {
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Post getPost()
    {
        return post;
    }

    public void setPost(Post post)
    {
        this.post = post;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Vote))
        {
            return false;
        }
        Vote that = (Vote) o;
        return ((user == null) ? 0 : user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid()) &&
            ((post == null) ? 0 : post.getPostid()) == ((that.post == null) ? 0 : that.post.getPostid());
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
