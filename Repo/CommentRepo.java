package lk.ac.vau.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.ac.vau.Model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long>
{

}
