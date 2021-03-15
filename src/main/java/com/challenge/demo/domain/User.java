package com.challenge.demo.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "site_id")
  private Long userId;

  @EqualsAndHashCode.Include
  @Column(nullable = false, name = "user_uuid", unique = true)
  private UUID userUUID;

  @EqualsAndHashCode.Include
  @Column(nullable = false, name = "username", unique = true)
  private String username;

  @OneToMany private List<QuestionAnswer> questionAnswers;
}
