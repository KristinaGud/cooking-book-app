package en.kristina.cookingbookapp.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;

@Data
@Entity
@Table(name = "recipes")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "local_id")
	private String localId;

	private String name;

	@Column(name = "source")
	private String sourceUrl;

	@Lob
	private String ingredients;

	@Column(name = "active")
	private Boolean isActive;

	@PastOrPresent
	@Column(name = "added_date")
	private Instant addedDate;

	private Long upvotes;
}
