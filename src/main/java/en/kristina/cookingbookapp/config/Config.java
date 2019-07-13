package en.kristina.cookingbookapp.config;

import en.kristina.cookingbookapp.communicator.service.QueueConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public QueueConsumer queueConsumer() {
		return new QueueConsumer();
	}
}
