package en.kristina.cookingbookapp.communicator.service;

import en.kristina.cookingbookapp.communicator.dto.RecipeDTO;

public interface RecipeConsumer {
	void consumeRecipe(RecipeDTO recipeDTO);
}
