package en.kristina.cookingbookapp.collector.config;

import com.google.gson.Gson;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@EnableRabbit
@Configuration
@ComponentScan("en.kristina.cookingbookapp.communicator")
public class RestConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Gson gson() { return new Gson(); }

	@Bean
	public ChromeDriver chromeDriver() {
		return new ChromeDriver();
	}
}