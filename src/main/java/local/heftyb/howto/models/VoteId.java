package local.heftyb.howto.models;

import java.io.Serializable;
import java.util.Objects;

public class VoteId implements Serializable
{
    private long user;

    private long post;

    public VoteId()
    {
    }

    public long getUser()
    {
        return user;
    }

    public void setUser(long user)
    {
        this.user = user;
    }

    public long getPost()
    {
        return post;
    }

    public void setPost(long post)
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
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        VoteId voteId = (VoteId) o;
        return user == voteId.user &&
            post == voteId.post;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(user,
            post);
    }
}
