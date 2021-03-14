package com.challenge.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "question")
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "question_id")
	private Long questionId;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "site_id")
	@EqualsAndHashCode.Include
	private Site site;

	@NotBlank
	@Length(min = 0, max = 250)
	@EqualsAndHashCode.Include
	private String question;

	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
	@EqualsAndHashCode.Include
	private List<QuestionAnswer> answers = new ArrayList<>();

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	@ManyToOne(cascade=CascadeType.ALL,  fetch = FetchType.EAGER)
	@JoinColumn(name="parent")
	private Question parent;

	@OneToMany(mappedBy="parent",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Question> children = new ArrayList<>();

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public Date getCreatedAt() {
		return createdAt;
	}

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public Date getUpdatedAt() {
		return updatedAt;
	}
}
