package en.kristina.cookingbookapp.collector.service;


import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import en.kristina.cookingbookapp.communicator.dto.RecipeDTO;
import en.kristina.cookingbookapp.communicator.service.CommunicatorService;
import en.kristina.cookingbookapp.communicator.service.QueueProducer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeriousEatsComService {


	@Value("${blog.address}")
	private String url;

	private final ChromeDriver chromeDriver;
	private final Gson gson;
	private final CommunicatorService communicatorService;
	private final QueueProducer queueProducer;

	@Transactional
	@Scheduled(fixedDelay = 2592000000L) //30 days after the last search
	public void consumeData() throws InterruptedException {
		chromeDriver.navigate().to(url);
		log.info("going to page");

		synchronized (chromeDriver) {
			chromeDriver.wait(3000);
			List<WebElement> links = chromeDriver.findElementsByClassName("module__link");

			Set<String> urlsSet = links.stream().map(a -> a.getAttribute("href")).limit(5).collect(Collectors.toSet());
			urlsSet.forEach(System.out::println);

			for (String url : urlsSet ) {
				log.info("found url: " + url);

				try {
					RecipeDTO recipe = new RecipeDTO();
					chromeDriver.navigate().to(url);
					chromeDriver.wait(3000);

					String name = chromeDriver.findElement(By.xpath("//h1[@class='title recipe-title']")).getText();
					log.info(name);
					String products = chromeDriver.findElement(By.xpath("//div[@class='recipe-ingredients']")).getText();
					log.info(products);
					String source = chromeDriver.getCurrentUrl();
					log.info("url: " + source);

					String generatedId = communicatorService.generateUniqueId(name, products);
					recipe.setLocalId(generatedId);
					recipe.setName(name);
					recipe.setIngredients(products);
					recipe.setSource(source);

					if (!recipe.getIngredients().isEmpty() && !recipe.getName().isEmpty()) {
						queueProducer.sendRecipe(gson.toJson(recipe));
						chromeDriver.wait(3000);
					}


				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}