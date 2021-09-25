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
import lk.ac.vau.Model.Post;
import lk.ac.vau.Repo.PostRepo;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	PostRepo context;

	@GetMapping
	public List<Post> getAll() {
		List<Post> posts=context.findAll();
		for (Post p: posts)
		{
			String ownUrl= linkTo(PostController.class).slash(p.getPostId()).toString();
			p.addLink(ownUrl, "own");
		}
		return posts;
	}

	@GetMapping("/{id}")
	public Post get(@PathVariable("id") Long id) {
		//http://localhost:8080/user/uid1101/comment
		String commentUrl= linkTo(PostController.class).slash(id).slash("comment").toString();
		Post post =context.findById(id).get();
		post.addLink(commentUrl, "Comment");
		return post;
	}

	@PostMapping
	public void add(@RequestBody Post post) {
		context.save(post);
	}

	@PutMapping
	public void update(@RequestBody Post post) {
		context.save(post);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		context.deleteById(id);
	}
	
	@GetMapping("/{id}/comment")
	public List<Comment> getComment(@PathVariable("id") Long id) {
		return context.findById(id).get().getComments();
	}	
}
