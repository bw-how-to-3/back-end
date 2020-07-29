package local.heftyb.howto.repository;

import local.heftyb.howto.models.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long>
{
}
