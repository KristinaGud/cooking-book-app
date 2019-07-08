package en.kristina.cookingbookapp.collector.service;

import com.google.gson.Gson;
import en.kristina.cookingbookapp.collector.dto.MyRecipe;
import en.kristina.cookingbookapp.collector.dto.RecipeBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import en.kristina.cookingbookapp.communicator.service.QueueProducer;
import en.kristina.cookingbookapp.communicator.service.CommunicatorService;
import en.kristina.cookingbookapp.communicator.dto.RecipeDTO;

import java.security.NoSuchAlgorithmException;
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

	private final RestTemplate restTemplate;
	private final Gson gson;
	private final QueueProducer queueProducer;
	private final CommunicatorService communicatorService;

	@Scheduled(fixedDelay = 86400000) //24h after the last search
	public void send50RecipesFromFood2ForkToSave() {
		for (int i = 0; i < 50; i++) {
			RecipeDTO recipe = getData();
			String serializedRecipe = gson.toJson(recipe);
			try {
				queueProducer.sendRecipe(serializedRecipe);
				log.info("Sending recipe " + recipe.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private RecipeDTO getData() {
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


		String title = extractedRecipe.getTitle();
		log.info(title);
		String ingredients = Arrays.toString(extractedRecipe.getIngredients());
		log.info("products: " + ingredients);

		RecipeDTO recipe = new RecipeDTO();
		String generatedUniqueId = null;
		try {
			generatedUniqueId = communicatorService.generateUniqueId(title, ingredients);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (generatedUniqueId != null) {
			recipe.setLocalId(generatedUniqueId);
			recipe.setName(title);
			recipe.setSource(extractedRecipe.getSource_url());
			recipe.setIngredients(ingredients);
		}

		return recipe;
	}


	private RecipeBody parseRecipe(String text) {
		log.info(text);
		return gson.fromJson(text, MyRecipe.class).getRecipe();
	}

	private String pickAtRandomRecipeId() {
		Random random = new Random();
		int pickedId = random.nextInt(60000);
		return Integer.toString(pickedId);
	}
}

