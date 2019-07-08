package en.kristina.cookingbookapp.communicator.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@EnableAutoConfiguration
//@Configuration
//public class RabbitMQConfig {

//	static final String topicExchangeName = "spring-boot-exchange";
//
//	@Value("${spring.rabbitmq.host}")
//	private String hostname;
//
//	@Value("${spring.rabbitmq.port}")
//	private int port;
//
//	@Value("${spring.rabbitmq.username}")
//	private String username;
//
//	@Value("${spring.rabbitmq.password}")
//	private String password;
//
//
//	@Bean
//	public CachingConnectionFactory connectionFactory() {
//		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//		connectionFactory.setHost(hostname);
//		connectionFactory.setPort(port);
//		connectionFactory.setUsername(username);
//		connectionFactory.setPassword(password);
//		return connectionFactory;
//	}
//
//	@Bean
//	TopicExchange exchange() {
//		return new TopicExchange(topicExchangeName);
//	}

//}
