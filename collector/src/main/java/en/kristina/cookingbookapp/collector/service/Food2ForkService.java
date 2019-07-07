package en.kristina.cookingbookapp.collector.service;

import com.google.gson.Gson;
import en.kristina.cookingbookapp.collector.dto.MyRecipe;
import en.kristina.cookingbookapp.collector.dto.RecipeBody;
import en.kristina.cookingbookapp.collector.entity.Recipe;
import en.kristina.cookingbookapp.collector.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class Food2ForkService {

	@Value("${api.food2fork.address}")
	private String urlToFood2Fork;

	@Value("${api.food2fork.key}")
	private String apiKey;

	private final RecipeRepository recipeRepository;
	private final RestTemplate restTemplate;
	private final Gson gson;

	@Transactional
	@Scheduled(fixedDelay = 86400000) //24h after the last search
	public void save50RecipesFromFood2Fork() {
		for (int i = 0; i < 2; i++) {
			Recipe recipeToSave = new Recipe();
			Recipe recipe = getData();
			recipeToSave.setIs_active(true);
			recipeToSave.setName(recipe.getName());
			recipeToSave.setSource_url(recipe.getSource_url());
			recipeToSave.setRecipe(recipe.getRecipe());
			recipeToSave.setAddedDate(Instant.now());
			log.info("recipe {} will be saved", recipeToSave.getName());
			recipeRepository.save(recipeToSave);
		}
	}

	public Recipe getData() {

		String resourceAddress = urlToFood2Fork
				.replace("{apiKey}", apiKey)
				.replace("{localRecipeId}", pickAtRandomRecipeId());

		log.info("url generalted: " + resourceAddress);
		String recipeJson = restTemplate.getForObject(resourceAddress, String.class);
		RecipeBody extractedRecipe = parseRecipe(recipeJson);
		log.info("Recipe Body: " + extractedRecipe.toString());

		if (extractedRecipe.equals("[]")) {
			throw new RuntimeException("Error when getting data from API");
		}

		log.info(extractedRecipe.getTitle());
		log.info("products: " + Arrays.toString(extractedRecipe.getIngredients()));
		Recipe recipe = new Recipe();

		recipe.setIs_active(true);
		recipe.setName(extractedRecipe.getTitle());
		recipe.setSource_url(extractedRecipe.getSource_url());
		recipe.setRecipe(Arrays.toString(extractedRecipe.getIngredients()));

		return recipe;
	}


	private RecipeBody parseRecipe(String text) {
		log.info(text);
		return gson.fromJson(text, MyRecipe.class).getRecipe();
	}

	private String pickAtRandomRecipeId() {
		Random random = new Random();
		Integer pickedId = random.nextInt(60000);
		return Integer.toString(pickedId);
	}
}

