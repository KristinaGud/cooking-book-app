package en.kristina.cookingbookapp.collector.dto;

import lombok.Data;

@Data
public class RecipeBody {
	String publisher;
	String f2f_url;
	String[] ingredients;
	String source_url;
	String recipe_id;
	String image_url;
	Float social_rank;
	String publisher_url;
	String title;
}
