package en.kristina.cookingbookapp.collector.dto;

import lombok.Data;

@Data
public class Recipe {
	private Long id;
	private String name;
	private String source_url;
	private String recipe;
}
