package en.kristina.cookingbookapp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.time.Instant;

@Data
@Entity
public class Alerts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	private String email;

	private String keyword;

	private Instant sentDate;
}
