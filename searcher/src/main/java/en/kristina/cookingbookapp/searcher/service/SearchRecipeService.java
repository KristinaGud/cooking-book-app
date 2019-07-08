package en.kristina.cookingbookapp.searcher.service;

import en.kristina.cookingbookapp.searcher.entity.SearchRecipe;
import en.kristina.cookingbookapp.searcher.repository.SearchRecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import en.kristina.cookingbookapp.communicator.service.QueueConsumer;
import en.kristina.cookingbookapp.communicator.dto.RecipeDTO;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchRecipeService {

	private  final SearchRecipeRepository recipeRepository;

	public Map<String, String> findAllRecipesByKeyword(String ingredient) throws IOException {
		return recipeRepository.findAllRecipesByIngredient(ingredient);
	}
}
