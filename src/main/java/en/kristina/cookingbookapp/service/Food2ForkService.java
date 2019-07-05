package en.kristina.cookingbookapp.service;

import en.kristina.cookingbookapp.entity.Recipe;
import en.kristina.cookingbookapp.repository.Food2ForkRepository;
import en.kristina.cookingbookapp.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class Food2ForkService {

	private final Food2ForkRepository food2ForkRepository;
	private final RecipeRepository recipeRepository;

	@Transactional
//	@Scheduled(fixedDelay = 86400000) //24h after the last search
	public void save50RecipesFromFood2Fork() {
		for (int i = 0; i < 10; i++) {
			log.info("starting");
			Recipe recipeToSave = new Recipe();
			Recipe recipe = food2ForkRepository.getData();
			recipeToSave.setIs_active(true);
			recipeToSave.setName(recipe.getName());
			recipeToSave.setSource_url(recipe.getSource_url());
//			recipeToSave.setRecipe(recipe.getRecipe()); Data truncation: Data too long for column 'recipe'
			log.info("recipe {} will be saved", recipeToSave.getName());
			recipeRepository.save(recipeToSave);
		}
	}

}
