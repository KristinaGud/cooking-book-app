package en.kristina.cookingbookapp.communicator.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {

	@Bean
	public Queue queueToSendToElastic() {
		return new Queue("all-recipes", true, false, false);
	}

	@Bean
	public Queue queueToSendToMysql() {
		return new Queue("all-recipes-mysql", true, false, false);
	}

	//TODO queue exchange
}
