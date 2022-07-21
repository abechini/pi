package com.esprit.bankPi.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	private String description;

	public int getNbrOfLikes() {
		return nbrOfLikes;
	}

	public void setNbrOfLikes(int nbrOfLikes) {
		this.nbrOfLikes = nbrOfLikes;
	}

	// @JsonIgnore
//	@ManyToOne
//	private User user;
	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
	private List<Comment> comments;
	@Value("${some.key:0}")
	private int nbrOfLikes;

}
