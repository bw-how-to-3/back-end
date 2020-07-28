package local.heftyb.howto.services;

import local.heftyb.howto.models.Post;

import java.util.List;

public interface PostService
{
    List<Post> findAll();

    Post findPostById(long id);

    Post findByTitle(String title);

    List<Post> findByTitleLike(String title);

    Post save(Post post);

    Post update(
        Post post,
        long id);

    void delete(long id);
}
