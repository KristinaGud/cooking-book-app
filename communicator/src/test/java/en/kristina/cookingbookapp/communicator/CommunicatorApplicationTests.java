package en.kristina.cookingbookapp.communicator;

import com.google.gson.Gson;
import en.kristina.cookingbookapp.communicator.dto.RecipeDTO;
import en.kristina.cookingbookapp.communicator.service.CommunicatorService;
import en.kristina.cookingbookapp.communicator.service.QueueProducer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunicatorApplicationTests {

	@Autowired
	private CommunicatorService service;

	@Autowired
	private QueueProducer producer;

	@Test
	public void sendAndReceivedMessagesIntegrationTest() throws NoSuchAlgorithmException {
		RecipeDTO recipe = new RecipeDTO();
		Gson gson = new Gson();

		String title = "Best Pancakes";
		String ingredients = "2 eggs, 1/6 cup milk, 1 cup of flour, 1 spoon sugar, olive oil";
		recipe.setLocalId(service.generateUniqueId(title,ingredients));
		recipe.setName(title);
		recipe.setIngredients(ingredients);
		recipe.setSource("https://magicrecipes.com/best-pancakes");
		String serializedRecipe = gson.toJson(recipe);

		String sentInfo = "";

		try {
			producer.sendRecipe(serializedRecipe);
			sentInfo = "message successfully sent";
		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertEquals("message successfully sent", sentInfo);
	}

}
