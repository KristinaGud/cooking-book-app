package en.kristina.cookingbookapp.communicator.service;

import com.google.gson.Gson;
import en.kristina.cookingbookapp.communicator.dto.RecipeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class QueueConsumer {

	@Autowired
	private RecipeConsumer recipeConsumer;
	@Autowired
	private Gson gson;

	@RabbitListener(queues = {"${queue.name}"})
	public void receiveRecipe(String recipe) {
		log.info("Received <" + recipe + ">");
		RecipeDTO recipeDTO = gson.fromJson(recipe, RecipeDTO.class);
		recipeConsumer.consumeRecipe(recipeDTO);
	}
}
