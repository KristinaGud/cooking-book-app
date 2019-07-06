package en.kristina.cookingbookapp.controller;

import en.kristina.cookingbookapp.service.Food2ForkService;
import en.kristina.cookingbookapp.service.SeriousEatsComService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class RecipeRestController {

	private final Food2ForkService food2ForkService;
	private final SeriousEatsComService seriousEatsComService;

	@GetMapping("/save")
	public void save() {
		food2ForkService.save50RecipesFromFood2Fork();

		try {
			seriousEatsComService.consumeData();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
