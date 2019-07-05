package en.kristina.cookingbookapp.repository;

import com.google.gson.Gson;
import en.kristina.cookingbookapp.dto.MyRecipe;
import en.kristina.cookingbookapp.dto.RecipeBody;
import en.kristina.cookingbookapp.entity.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class Food2ForkRepository {

	@Value("${api.food2fork.address}")
	private String urlToFood2Fork;

	@Value("${api.food2fork.key}")
	private String apiKey;

	private final RestTemplate restTemplate;
	private final Gson gson;

	public Recipe getData() {

		String resourceAddress = urlToFood2Fork
				.replace("{apiKey}", apiKey)
				.replace("{localRecipeId}", pickAtRandomRecipeId());

		log.info("url generalted: " + resourceAddress);
		String recipeJson = restTemplate.getForObject(resourceAddress, String.class);
		RecipeBody extractedRecipe = parseRecipe(recipeJson);
		log.info("Recipe Body: " + extractedRecipe.toString());

		if (extractedRecipe.equals("")) {
			throw new RuntimeException("Error when getting data from API");
		}

		log.info(extractedRecipe.getTitle());
		log.info("ingrdienty: " + Arrays.toString(extractedRecipe.getIngredients()));
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
