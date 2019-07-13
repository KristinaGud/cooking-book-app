package en.kristina.cookingbookapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import en.kristina.cookingbookapp.searcher.service.SearchRecipeService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api")
public class RecipeRestController {

	private final SearchRecipeService searchRecipeService;

	@GetMapping("/recipe/{product}")
	public List<String> allRecipesByproduct(@PathVariable String product) throws IOException {
		Map<String, String> allRecipesByKeyword = searchRecipeService.findAllRecipesByKeyword(product);
		return new ArrayList<>(allRecipesByKeyword.keySet());
	}
}
