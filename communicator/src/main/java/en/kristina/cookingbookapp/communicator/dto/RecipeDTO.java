package en.kristina.cookingbookapp.communicator.dto;

import lombok.Data;

@Data
public class RecipeDTO {
	private String localId;
	private String name;
	private String source;
	private String ingredients;
}
