package local.heftyb.howto.repository;

import local.heftyb.howto.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long>
{
    Post findByTitleIgnoreCase(String title);

    List<Post> findByTitleContainingIgnoreCase(String title);
}
