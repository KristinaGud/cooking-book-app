package en.kristina.cookingbookapp.entity;

import lombok.Data;
import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.validation.constraints.Email;


@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String userName;

	private String password;

	@Column(unique = true)
	@Email
	private String email;

	private LocalDate last_login;
}
