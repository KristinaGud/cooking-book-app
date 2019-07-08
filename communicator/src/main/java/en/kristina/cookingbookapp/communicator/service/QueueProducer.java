package en.kristina.cookingbookapp.communicator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class QueueProducer {

	private final RabbitTemplate rabbitTemplate;
	private final QueueConsumer queueConsumer;
	private final Queue queue;

	public void sendRecipe(String recipe) throws Exception {
		log.info("Sending > " + recipe);
		rabbitTemplate.convertAndSend(queue.getName(), recipe);
		queueConsumer.getLatch().await(3000, TimeUnit.MILLISECONDS);
	}
}
