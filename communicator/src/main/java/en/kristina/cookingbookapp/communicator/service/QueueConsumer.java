package en.kristina.cookingbookapp.communicator.service;

import en.kristina.cookingbookapp.communicator.dto.RecipeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class QueueConsumer {

	private CountDownLatch latch = new CountDownLatch(1);

	@RabbitListener(queues = "${queue.name}")
	public void receiveRecipe(String recipe) {
		log.info("Received <" + recipe + ">");
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
