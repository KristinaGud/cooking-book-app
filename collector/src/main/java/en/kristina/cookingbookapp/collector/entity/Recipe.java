package en.kristina.cookingbookapp.collector.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
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

	@Column(nullable = false, unique = true)
	private String name;

	private String source_url;

	@Column
	@Lob
	private String recipe;

	private Boolean is_active;

	@PastOrPresent
	@DateTimeFormat
	private Instant addedDate;
}
