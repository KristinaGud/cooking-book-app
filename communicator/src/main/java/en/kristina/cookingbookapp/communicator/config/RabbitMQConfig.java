package en.kristina.cookingbookapp.communicator.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {

	@Bean
	public Queue queue() {
		return new Queue("all-recipes", true, false, false);
	}
}
