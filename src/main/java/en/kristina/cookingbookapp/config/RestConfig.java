package en.kristina.cookingbookapp.config;

import com.google.gson.Gson;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@Configuration
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
