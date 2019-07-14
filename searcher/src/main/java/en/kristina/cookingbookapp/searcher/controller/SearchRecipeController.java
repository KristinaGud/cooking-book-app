package en.kristina.cookingbookapp.searcher.controller;

import en.kristina.cookingbookapp.searcher.service.SearchRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class SearchRecipeController {

	private final SearchRecipeService searchRecipeService;

	@GetMapping("/recipe/{product}")
	public List<String> allRecipesByproduct(@PathVariable String product) throws IOException {
		Map<String, String> allRecipesByKeyword = searchRecipeService.findAllRecipesByKeyword(product);
		return new ArrayList<>(allRecipesByKeyword.keySet());
	}
}
