package en.kristina.cookingbookapp.communicator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class QueueProducer {

	private final RabbitTemplate rabbitTemplate;
	private final Queue queue;

	public void sendRecipe(String recipe) {
		log.info("Sending > " + recipe);
		rabbitTemplate.convertAndSend(queue.getName(), recipe);
	}
}
