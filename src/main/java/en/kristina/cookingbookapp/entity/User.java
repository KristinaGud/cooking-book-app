package en.kristina.cookingbookapp.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;

@Entity
@Data
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;

	@Email
	@Column(unique = true)
	private String email;

	@PastOrPresent
	@Column(name = "registered")
	private Instant registeredDate;

	@PastOrPresent
	@Column(name = "last_login")
	private Instant lastLogin;
}
