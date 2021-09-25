package lk.ac.vau.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {

	@Id
	private long PostId;
	private String Title;
	private String Content;
	
	@Transient
	private List<Link>links=new ArrayList<Link>();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date CreatedOn;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "owner_id",referencedColumnName = "UserdId")
	private User Owner;
	
	@OneToMany(mappedBy = "CommentedIn")
	private List<Comment>comments;
	
	public Post()
	{}

	public Post(long postId, String title, String content, List<Link> links, Date createdOn, User owner,
			List<Comment> comments) {
		super();
		PostId = postId;
		Title = title;
		Content = content;
		this.links = links;
		CreatedOn = createdOn;
		Owner = owner;
		this.comments = comments;
	}

	public long getPostId() {
		return PostId;
	}

	public void setPostId(long postId) {
		PostId = postId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public Date getCreatedOn() {
		return CreatedOn;
	}

	public void setCreatedOn(Date createdOn) {
		CreatedOn = createdOn;
	}

	@JsonIgnore
	public User getOwner() {
		return Owner;
	}

	public void setOwner(User owner) {
		Owner = owner;
	}

	@JsonIgnore
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void addLink(String uri, String rel) {
		Link link=new Link(uri,rel);
		links.add(link);
	}
}
