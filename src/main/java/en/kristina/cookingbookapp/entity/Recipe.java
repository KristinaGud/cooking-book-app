//package en.kristina.cookingbookapp.entity;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.persistence.*;
//import javax.validation.constraints.PastOrPresent;
//import java.time.Instant;
//
//@Data
//@Entity
//@Table(name = "recipes")
//public class Recipe {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@Column(nullable = false, unique = true)
//	private String name;
//
//	private String sourceUrl;
//
//	@Column
//	@Lob
//	private String recipe;
//
//	private Boolean isActive;
//
//	@PastOrPresent
//	@DateTimeFormat
//	private Instant addedDate;
//
//	private Long upvotes;
//}
