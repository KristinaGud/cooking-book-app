package en.kristina.cookingbookapp.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;

@Data
@Entity
@Table(name = "alerts")
public class Alert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private String keyword;

	@PastOrPresent
	@Column(name = "sent_date")
	private Instant sentDate;
}
