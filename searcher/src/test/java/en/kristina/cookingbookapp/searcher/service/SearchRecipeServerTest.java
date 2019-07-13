package en.kristina.cookingbookapp.searcher.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchRecipeServerTest {

	@Autowired
	private SearchRecipeService searchRecipeService;

	@Test
	public void findRecipesByKeywordTest() {
		Map<String, String> recipesContainingSyrup = null;
		Map<String, String> recipesContainingRice = null;
		try {
			recipesContainingSyrup = searchRecipeService.findAllRecipesByKeyword("syrup");
			recipesContainingRice = searchRecipeService.findAllRecipesByKeyword("rice");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Assert.assertTrue(recipesContainingSyrup.containsKey("Glenn's Marinated Pork Shoulder"));
		Assert.assertTrue(recipesContainingRice.containsKey("Seared Ahi with Brown Rice and Pineapple-Ginger Broth"));
	}
}
