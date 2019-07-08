package en.kristina.cookingbookapp.searcher.service;

import en.kristina.cookingbookapp.communicator.dto.RecipeDTO;
import en.kristina.cookingbookapp.communicator.service.RecipeConsumer;
import en.kristina.cookingbookapp.searcher.entity.SearchRecipe;
import en.kristina.cookingbookapp.searcher.repository.SearchRecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@RequiredArgsConstructor
@Component
public class SearchRecipeConsumer implements RecipeConsumer {

	private final SearchRecipeRepository searchRecipeRepository;

	@Override
	public void consumeRecipe(RecipeDTO recipeDTO) {
		SearchRecipe recipeToSave = new SearchRecipe();
		recipeToSave.setLocalId(recipeDTO.getLocalId());
		recipeToSave.setName(recipeDTO.getName());
		recipeToSave.setUrl(recipeDTO.getSource());
		recipeToSave.setIngredients(recipeDTO.getIngredients());
		recipeToSave.setAddedDate(Instant.now());
		searchRecipeRepository.add(recipeToSave);
	}
}
