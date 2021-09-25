package lk.ac.vau.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import lk.ac.vau.Model.Comment;
import lk.ac.vau.Repo.CommentRepo;


@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentRepo context;
	
	@GetMapping
	public List<Comment> getAll()
	{
		List<Comment> comments=context.findAll();
		for (Comment c: comments)
		{
			String ownUrl= linkTo(CommentController.class).slash(c.getCommentId()).toString();
			c.addLink(ownUrl, "own");
		}
		return comments;
	}
	
	@GetMapping("/{id}")
	public Comment get(@PathVariable("id") long id)
	{
		return context.findById(id).get();
	}
	
	@PostMapping
	public void add(@RequestBody Comment comment)
	{
		context.save(comment);
	}
	
	@PutMapping
	public void update(@RequestBody Comment comment)
	{
		context.save(comment);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") long id)
	{
		context.deleteById(id);
	}
}
