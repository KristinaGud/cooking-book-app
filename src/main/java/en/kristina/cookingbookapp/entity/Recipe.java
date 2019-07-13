package en.kristina.cookingbookapp.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

	private String localId;

	private String name;

	private String sourceUrl;

	@Column
	@Lob
	private String ingredients;

	private Boolean isActive;

	private Instant addedDate;

	private Long upvotes;
}
