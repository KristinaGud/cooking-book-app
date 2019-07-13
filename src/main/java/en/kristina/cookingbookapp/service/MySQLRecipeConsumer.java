package en.kristina.cookingbookapp.service;

import en.kristina.cookingbookapp.communicator.service.RecipeConsumer;
import en.kristina.cookingbookapp.entity.Recipe;
import en.kristina.cookingbookapp.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import en.kristina.cookingbookapp.communicator.dto.RecipeDTO;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class MySQLRecipeConsumer implements RecipeConsumer {

	private final RecipeRepository recipeRepository;

	@Override
	public void consumeRecipe(RecipeDTO recipeDTO) {
		log.info("recipe reserved in MYSQL: " + recipeDTO.getName());
		Recipe recipeToSave = new Recipe();
		recipeToSave.setIsActive(true);
		recipeToSave.setLocalId(recipeDTO.getLocalId());
		recipeToSave.setName(recipeDTO.getName());
		recipeToSave.setSourceUrl(recipeDTO.getSource());
		recipeToSave.setIngredients(recipeDTO.getIngredients());
		recipeToSave.setAddedDate(Instant.now());
		recipeToSave.setUpvotes(0L);
		log.info("saving in mysql " + recipeToSave.getName());
		recipeRepository.save(recipeToSave);
	}
}
