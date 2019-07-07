package en.kristina.cookingbookapp.searcher.entity;

import lombok.Data;

import java.time.Instant;

@Data
public class SearchRecipe {
	private Long id;
	private String name;
	private String url;
	private String ingredients;
	//private Instant addedDate;
}
